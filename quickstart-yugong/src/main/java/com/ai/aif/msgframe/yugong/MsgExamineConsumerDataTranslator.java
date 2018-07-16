/**
 * 项目名称：yugong 
 * 文件名：MsgExamineConsumerDataTranslator.java
 * 版本信息：
 * 日期：2018年7月14日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.ai.aif.msgframe.yugong;

import com.taobao.yugong.common.model.record.IncrementOpType;
import com.taobao.yugong.common.model.record.IncrementRecord;
import com.taobao.yugong.common.model.record.Record;
import com.taobao.yugong.translator.AbstractDataTranslator;
import com.taobao.yugong.translator.DataTranslator;

/**
 * MsgExamineConsumerDataTranslator
 * 
 * @author：yangzl@asiainfo.com
 * @2018年7月14日 上午12:07:43
 * @since 1.0
 */
public class MsgExamineConsumerDataTranslator extends AbstractDataTranslator implements DataTranslator {

    @Override
    public boolean translator(Record record) {
        if (((record instanceof IncrementRecord)) && (IncrementOpType.D == ((IncrementRecord) record).getOpType())) {
            return false;
        }
        if (((record instanceof IncrementRecord)) && (IncrementOpType.U == ((IncrementRecord) record).getOpType())) {
            return false;
        }
        return super.translator(record);
    }
}
