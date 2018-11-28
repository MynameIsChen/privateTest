package com.chen.test.longconnect;

import android.util.Log;

import com.chen.core.bean.MinaEntity;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Created by chenxianglin on 2018/5/29.
 * Class note:
 */

public class AppProtocolCodecFactory implements ProtocolCodecFactory {
    private String tag = "AppProtocolCodecFactory";

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        //编码器，可以通过它将写入的对象转为写入的数组
        return new AppProtocolEncoder();
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        //解码器，通过它将读入的数组转为我们想看到的对象
        return new AppProtocolDecoder();
    }


    public class AppProtocolEncoder extends ProtocolEncoderAdapter {
        @Override
        public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
            //这个Object是发送数据时传入的那个Object，就是我们使用的对象
            MinaEntity m = (MinaEntity) o;
            Log.e(tag, "encode");
            if (o == null) {
                Log.e(tag, "AppSocketMessage is null");
            } else {
                IoBuffer buffer = IoBuffer.allocate(20);
                // 自动扩容
                buffer.setAutoExpand(true);
                // 自动收缩
                buffer.setAutoShrink(true);
//                buffer.put(getSocketSendData(m));//把对象转为数组类型
                buffer.flip();
                protocolEncoderOutput.write(buffer);

            }
        }
    }



    public class AppProtocolDecoder extends ProtocolDecoderAdapter {
        @Override
        public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
            Log.e(tag, "decode");
            int limit = ioBuffer.limit();
            Log.e(tag,"decode num :"+limit);
            byte[] bytes = new byte[limit];
            ioBuffer.get(bytes);
            MinaEntity m = new MinaEntity();//把服务器数据读进来的是数组，要转为我们想看到的对象
//            if (limit >= AppSocketHead.HEAD_LENGTH) {
//                m.getResponseHeader() = getHead(bytes);//返回的数组中读取信息头
//                m.getResponseBody() = getResponseBody(bytes);//返回的数组中读取信息体
//            }
            protocolDecoderOutput.write(m);

        }
    }
}
