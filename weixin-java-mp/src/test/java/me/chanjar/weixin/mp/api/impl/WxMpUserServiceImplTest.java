
package me.chanjar.weixin.mp.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.ApiTestModule;
import me.chanjar.weixin.mp.api.WxXmlMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpUserQuery;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

/**
 * 测试用户相关的接口
 *
 * @author chanjarster
 * @author Binary Wang
 */
@Test(groups = "userAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpUserServiceImplTest {

  @Inject
  private WxMpServiceImpl wxService;

  private WxXmlMpInMemoryConfigStorage configProvider;

  @BeforeTest
  public void setup() {
    this.configProvider = (WxXmlMpInMemoryConfigStorage) this.wxService
        .getWxMpConfigStorage();
  }

  public void testUserUpdateRemark() throws WxErrorException {
    this.wxService.getUserService()
        .userUpdateRemark(this.configProvider.getOpenid(), "测试备注名");
  }

  public void testUserInfo() throws WxErrorException {
    WxMpUser user = this.wxService.getUserService()
        .userInfo(this.configProvider.getOpenid(), null);
    Assert.assertNotNull(user);
    System.out.println(user);
  }

  public void testUserInfoList() throws WxErrorException {
    List<String> openids = new ArrayList<String>();
    openids.add(this.configProvider.getOpenid());
    List<WxMpUser> userList = this.wxService.getUserService()
        .userInfoList(openids);
    Assert.assertEquals(userList.size(), 1);
    System.out.println(userList);
  }

  public void testUserInfoListByWxMpUserQuery() throws WxErrorException {
    WxMpUserQuery query = new WxMpUserQuery();
    query.add(this.configProvider.getOpenid(), "zh_CN");
    List<WxMpUser> userList = this.wxService.getUserService()
        .userInfoList(query);
    Assert.assertEquals(userList.size(), 1);
    System.out.println(userList);
  }

  public void testUserList() throws WxErrorException {
    WxMpUserList wxMpUserList = this.wxService.getUserService().userList(null);
    Assert.assertNotNull(wxMpUserList);
    Assert.assertFalse(wxMpUserList.getCount() == -1);
    Assert.assertFalse(wxMpUserList.getTotal() == -1);
    Assert.assertFalse(wxMpUserList.getOpenIds().size() == -1);
    System.out.println(wxMpUserList);
  }

}
