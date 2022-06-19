package factory;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

    @DataProvider(name = "data")
    public Object[][] getDataFromExcelUsingFillo(Method test) throws FilloException {
        //Object[][] obj = new Object[1][1];
        Fillo file= new Fillo();
        Connection connection=file.getConnection(System.getProperty("user.dir") + "\\src\\data\\TestData.xlsx");
        Recordset rs= connection.executeQuery("Select * from Sheet1 where TestID='"+test.getName()+"'");
        int rowCnt=rs.getCount();
        int colCnt=rs.getFieldNames().size();

        Object[][] obj= new Object[rowCnt][1];
        int row=0;
        while(rs.next())
        {
            HashMap<String,String> dataMap=new HashMap<String, String>();
            for(int col=0;col<colCnt;col++)
            {
                dataMap.put(rs.getField(col).name(), String.valueOf(rs.getField(col)));
//                rs.getField(col);
//                rs.getField(col).name();
            }
            System.out.println(dataMap);
            obj[row][1]=dataMap;
        }
        return obj;
    }
}
