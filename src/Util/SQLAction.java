package Util;

import java.sql.SQLException;

/**
 * Created by Tnecesoc on 2016/9/7.
 */
@FunctionalInterface
public interface SQLAction {

    void act() throws SQLException;

}
