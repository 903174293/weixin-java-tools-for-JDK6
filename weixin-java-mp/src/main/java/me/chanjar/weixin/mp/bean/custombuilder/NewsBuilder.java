package me.chanjar.weixin.mp.bean.custombuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage.WxArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息builder
 * <pre>
 * 用法:
 * WxMpCustomMessage m = WxMpCustomMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 * @author chanjarster
 *
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {

  private List<WxMpCustomMessage.WxArticle> articles = new ArrayList<WxArticle>();
  
  public NewsBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_NEWS;
  }

  public NewsBuilder addArticle(WxMpCustomMessage.WxArticle article) {
    this.articles.add(article);
    return this;
  }

  @Override
  public WxMpCustomMessage build() {
    WxMpCustomMessage m = super.build();
    m.setArticles(this.articles);
    return m;
  }
}
