package be.ucll.da.dentravak.csv;

import be.ucll.da.dentravak.model.Order;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CsvView extends AbstractCsvView {
    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"orders.csv\"");

        List<Order> orders = (List<Order>) model.get("orders");
        String[] header = {"id","mobilePhoneNumber","name","sandwichId","breadType","price","creationDate","printed"};
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        csvWriter.writeHeader(header);

        for(Order order : orders){
            csvWriter.write(order, header);
        }
        csvWriter.close();
    }
}
