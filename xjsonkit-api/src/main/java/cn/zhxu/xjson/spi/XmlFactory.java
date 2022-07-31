package cn.zhxu.xjson.spi;

import cn.zhxu.data.DataConvertor;

import java.util.ServiceLoader;

public interface XmlFactory {

    DataConvertor create();

    static DataConvertor find() {
        for (XmlFactory factory : ServiceLoader.load(XmlFactory.class)) {
            DataConvertor convertor = factory.create();
            if (convertor != null) {
                return convertor;
            }
        }
        throw new IllegalStateException("No XmlFactory found");
    }

}
