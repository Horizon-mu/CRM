package com.hrz.crm.workbench.service.impl;

import com.hrz.crm.utils.DateTimeUtil;
import com.hrz.crm.utils.UUIDUtil;
import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.dao.*;
import com.hrz.crm.workbench.entity.*;
import com.hrz.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueDao clueDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ContactsDao contactsDao;
    @Autowired
    private ClueRemarkDao clueRemarkDao;
    @Autowired
    private CustomerRemarkDao customerRemarkDao;
    @Autowired
    private ContactsRemarkDao contactsRemarkDao;
    @Autowired
    private ClueActivityRelationDao clueActivityRelationDao;
    @Autowired
    private TranDao tranDao;
    @Autowired
    private ContactsActivityRelationDao contactsActivityRelationDao;
    @Autowired
    private TranHistoryDao tranHistoryDao;
    @Override
    public boolean doSave(Clue clue) {
        int n = clueDao.saveClue(clue);
        if (1 == n)
            return true;
        return false;
    }

    @Override
    public PaginationVo doPageList(Map<String, Object> map) {
        //得到记录条数
        int total = clueDao.getCountByCondition(map);
        //得到线索列表
        List<Clue> list = clueDao.doPageList(map);
        PaginationVo<Clue> paginationVo = new PaginationVo<>();

        paginationVo.setTotal(total);
        paginationVo.setDataList(list);
        return paginationVo;
    }

    @Override
    public Clue getClueById(String id) {
        return clueDao.getClueById(id);
    }

    @Override
    public boolean deleteById(String[] id) {
        return true;
    }

    @Override
    @Transactional
    public boolean convert(String clueId, Tran tran, String createBy) {
        String createTime = DateTimeUtil.getSysTime();
        boolean flag = true;
        //通过线索id得到线索对象
        Clue clue = clueDao.getClueById(clueId);
        //得到客户（公司）的名称
        String company = clue.getCompany();
        //得到客户的信息,当客户不存在时，新建客户

        Customer customer = customerDao.getCustomerByName(company);
        if(customer == null){
            //如果customer为空，说明没有这个客户，需要新建一个
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setAddress(clue.getAddress());
            customer.setWebsite(clue.getWebsite());
            customer.setPhone(clue.getPhone());
            customer.setOwner(clue.getOwner());
            customer.setName(company);
            customer.setDescription(clue.getDescription());
            customer.setCreateBy(createBy);
            customer.setCreateTime(createTime);
            customer.setContactSummary(clue.getContactSummary());
            System.out.println("customer======================================="+customer);
            //添加客户
            int count1 = customerDao.save(customer);
            if (count1 != 1){
                flag = false;
            }
        }

        //经过第二步处理后，客户的信息已经拥有了，将来在处理其他表的时候，如果要使用到客户的id
        //直接使用customer.getId() 拿id就可以了

        //3.通过线索对象提取联系人信息，保存联系人
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setSource(clue.getSource());
        contacts.setOwner(clue.getOwner());
        contacts.setAddress(clue.getAddress());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setCreateBy(createBy);
        contacts.setCreateTime(createTime);
        contacts.setAppellation(clue.getAppellation());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setJob(clue.getJob());
        contacts.setMphone(clue.getMphone());
        contacts.setEmail(clue.getEmail());
        contacts.setFullname(clue.getFullname());
        contacts.setDescription(clue.getDescription());
        contacts.setCustomerId(customer.getId());
        //添加联系人
        int count2 = contactsDao.save(contacts);
        if (count2 != 1){
            flag = false;
        }

        //经过第三步处理后，联系人的信息已经拥有了，将来在处理其他表的时候，如果要使用到联系人的id
        //直接使用contacts.getId();

        //4.线索备注转换到客户备注以及联系人备注
        //根据clueId查询出与该线索关联的备注信息列表
        List<ClueRemark> clueRemarkList = clueRemarkDao.getListByClueId(clueId);
        //取出每一条线索的备注
        for (ClueRemark clueRemark : clueRemarkList){
            //取出备注信息(主要转换到客户备注和联系人备注的就是这个备注信息)
            String noteContent = clueRemark.getNoteContent();

            //创建客户备注对象，添加客户备注
            CustomerRemark customerRemark = new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setCreateBy(createBy);
            customerRemark.setCreateTime(createTime);
            customerRemark.setCustomerId(customer.getId());
            customerRemark.setEditFlag("0");
            customerRemark.setNoteContent(noteContent);
            int count3 = customerRemarkDao.save(customerRemark);
            if (count3 != 1){
                flag = false;
            }

            //创建联系人备注对象，添加联系人
            ContactsRemark contactsRemark = new ContactsRemark();
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setCreateBy(createBy);
            contactsRemark.setCreateTime(createTime);
            contactsRemark.setContactsId(contacts.getId());
            contactsRemark.setEditFlag("0");
            contactsRemark.setNoteContent(noteContent);
            int count4 = contactsRemarkDao.save(contactsRemark);
            if (count4 != 1){
                flag = false;
            }
        }

        //5.“线索市场活动”的关系转换到“联系人和市场活动”的关系
        //查询出与该条线索关联的市场活动，查询与市场活动的关联关系列表
        List<ClueActivityRelation> clueActivityRelationList = clueActivityRelationDao.getListByClueId(clueId);
        //遍历每一条与市场活动关联的关联关系记录
        for (ClueActivityRelation clueActivityRelation : clueActivityRelationList){
            //从每一条遍历出来的记录中取出关联的市场活动id
            String activityId = clueActivityRelation.getActivityId();
            //创建联系人与市场活动的关联关系对象，让第三步生成的联系人与市场活动做关联
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityId(activityId);
            contactsActivityRelation.setContactsId(contacts.getId());
            //添加联系人与市场活动的关联关系
            int count5 = contactsActivityRelationDao.save(contactsActivityRelation);
            if (count5 != 1){
                flag = false;
            }
        }

        //6.如果有创建交易需求，创建一条交易
        if (tran != null){
            /*
                clue对象在controller里面已经封装好的信息如下:
                    id,money,name,expectedDate,stage,activityId,createBy,createTime
                接下来可以通过第一步生成的clue对象，取出一些信息，继续完善对tran对象的封装.根据用户需求来。
             */
            tran.setId(UUIDUtil.getUUID());
            tran.setSource(clue.getSource());
            tran.setOwner(clue.getOwner());
            tran.setNextContactTime(clue.getNextContactTime());
            tran.setDescription(clue.getDescription());
            tran.setCustomerId(customer.getId());
            tran.setContactSummary(clue.getContactSummary());
            tran.setContactsId(contacts.getId());
            //添加交易
            int count6 = tranDao.save(tran);
            if (count6 != 1){
                flag = false;
            }

            //7.如果创建了交易，则创建一条该交易下的交易历史
            TranHistory tranHistory = new TranHistory();
            tranHistory.setId(UUIDUtil.getUUID());
            tranHistory.setCreateBy(createBy);
            tranHistory.setCreateTime(createTime);
            tranHistory.setExpectedDate(tran.getExpectedDate());
            tranHistory.setMoney(tran.getMoney());
            tranHistory.setStage(tran.getStage());
            tranHistory.setTranId(tran.getId());
            //添加交易历史
            int count7 = tranHistoryDao.save(tranHistory);
            if (count7 != 1){
                flag = false;
            }
        }

        //8.删除线索备注
        /*for (ClueRemark clueRemark : clueRemarkList){
            int count8 = clueRemarkDao.delete(clueRemark);
            if (count8 != 1){
                flag = false;
            }
        }
        //9.删除线索和市场活动的关系
        for(ClueActivityRelation clueActivityRelation : clueActivityRelationList){
            int count9 = clueActivityRelationDao.delete(clueActivityRelation);
            if (count9 != 1){
                flag = false;
            }
        }
        //10，删除线索
        int count10 = clueDao.delete(clueId);
        if (count10 != 1){
            flag = false;
        }*/

        return flag;
    }
}
