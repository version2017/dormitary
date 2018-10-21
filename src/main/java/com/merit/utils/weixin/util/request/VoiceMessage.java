package com.merit.utils.weixin.util.request;
/** 
 * ��Ƶ��Ϣ 
 *  
 * @author ��� 
 * @date 2013-09-10 
 */  
public class VoiceMessage extends BaseMessage {  
    // ý��ID  
    private String MediaId;  
    // ������ʽ  
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  
}
