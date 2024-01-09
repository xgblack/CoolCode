package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.listener.DataInsertListener;
import com.xgblack.cool.framework.mybatis.listener.DataUpdateListener;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_oauth_client_detail", onInsert = DataInsertListener.class, onUpdate = DataUpdateListener.class)
public class OauthClientDO {

}
