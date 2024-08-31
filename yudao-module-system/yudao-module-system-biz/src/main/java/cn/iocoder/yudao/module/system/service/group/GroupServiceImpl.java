package cn.iocoder.yudao.module.system.service.group;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.group.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.group.GroupDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.group.GroupMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 用户组 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Override
    public Long createGroup(GroupSaveReqVO createReqVO) {
        // 插入
        GroupDO group = BeanUtils.toBean(createReqVO, GroupDO.class);
        groupMapper.insert(group);
        // 返回
        return group.getId();
    }

    @Override
    public void updateGroup(GroupSaveReqVO updateReqVO) {
        // 校验存在
        validateGroupExists(updateReqVO.getId());
        // 更新
        GroupDO updateObj = BeanUtils.toBean(updateReqVO, GroupDO.class);
        groupMapper.updateById(updateObj);
    }

    @Override
    public void deleteGroup(Long id) {
        // 校验存在
        validateGroupExists(id);
        // 删除
        groupMapper.deleteById(id);
    }

    private void validateGroupExists(Long id) {
        if (groupMapper.selectById(id) == null) {
            throw exception(GROUP_NOT_EXISTS);
        }
    }

    @Override
    public GroupDO getGroup(Long id) {
        return groupMapper.selectById(id);
    }

    @Override
    public PageResult<GroupDO> getGroupPage(GroupPageReqVO pageReqVO) {
        return groupMapper.selectPage(pageReqVO);
    }

}