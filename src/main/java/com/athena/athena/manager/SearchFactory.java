package com.athena.athena.manager;

public class SearchFactory {
    public static SearchFieldregister getSearchFactory(String filedName){
        switch (filedName){
            case "replyId":
                return new SearchFieldByReplyId();
            case "said":
                return new SearchFieldBySaid();
            case "reply":
                return new SearchFieldByReply();
        }
        return new SearchFieldDefault();
    }
}
