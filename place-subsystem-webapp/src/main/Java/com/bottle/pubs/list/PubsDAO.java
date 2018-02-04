package com.bottle.pubs.list;

import java.sql.SQLException;
import java.util.List;

public interface PubsDAO {
    List getAllPubs() throws SQLException;
}
