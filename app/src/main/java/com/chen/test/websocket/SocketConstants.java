package com.chen.test.websocket;

/**
 * Created by chenlei on 2018/3/7.
 */

public class SocketConstants
{
  public static final String SOCKET_IP = "192.168.2.162";
  public static final int SOCKET_PORT = 8080;
  public static final String SOCKET_RECEIVE_HEARTBEAT = "{\"info\":\"ping\"}";//心跳接收包
  public static final String SOCKET_SEND_HEARTBEAT = "{\"info\":\"pong\"}";//心跳发送包

  public static final int SOCKET_JSON_FAILED_CODE = -100;//解析JOSN失败
  public static final int SOCKET_SUCCESS_CODE = 200;//方法请求成功

  public static final int SOCKET_CONNECT_TYPE = 1;//链接操作
  public static final int SOCKET_START_ACTIVITY_TYPE = 1001;//登录操作 开始活动
  public static final int SOCKET_SEND_LOCATION_TYPE = 1002;//发送地理位置
  public static final int SOCKET_NOTICE_BEGAN_SPEAK_TYPE = 1003;//通知我要讲话
  public static final int SOCKET_SEND_SPEAK_TYPE = 1004;//发送对讲信息
  public static final int SOCKET_EXIT_ACTIVITY_TYPE = 1005;//退出活动

  public static final int SOCKET_JOIN_ACTIVITY_TYPE = 2001;//接收到有人加入活动
  public static final int SOCKET_RECEIVE_LOCATION_TYPE = 2002;//接收到地理位置
  public static final int SOCKET_RECEIVE_BEGAN_SPEAK_TYPE = 2003;//接收到有人要讲话
  public static final int SOCKET_RECEIVE_SPEAK_TYPE = 2004;//接收到语音内容
  public static final int SOCKET_RECEIVE_EXIT_ACTIVITY_TYPE = 2005;//接收到有人退出
}
