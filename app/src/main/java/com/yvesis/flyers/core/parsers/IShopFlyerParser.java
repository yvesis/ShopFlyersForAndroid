package com.yvesis.flyers.core.parsers;

/**
 * Created by louly on 2017-01-30.
 */

public interface  IShopFlyerParser<T> {

    public T parse(String content);
    public T parseFile(String filename);

}
