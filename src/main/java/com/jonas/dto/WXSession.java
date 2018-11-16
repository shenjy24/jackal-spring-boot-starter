package com.jonas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/11/02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WXSession {
    private String openId;
    private String sessionKey;
}
