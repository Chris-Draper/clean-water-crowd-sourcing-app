import controller.WaterSourceReportController;
import model.WaterSourceReport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nharper32 on 11/9/16.
 */
public class WaterSourceReportControllerTest {

    private static final int TIMEOUT = 200;
    private WaterSourceReportController ctrl;
    private WaterSourceReport report;

    @Before
    public void setUp() {
        ctrl = new WaterSourceReportController();


    }

    @Test(timeout = TIMEOUT)
    public void areFieldsCompleted() throws Exception {
        assertEquals(ctrl.isCompleted("32.02", "-84.03", WaterSourceReport.WaterType.BT, WaterSourceReport.WaterCondition.C), true);
    }

    @Test(timeout = TIMEOUT)
    public void isLatCompleted() throws Exception {
        assertEquals(ctrl.isCompleted("", "-84.03", WaterSourceReport.WaterType.BT, WaterSourceReport.WaterCondition.C), false);
    }

    @Test(timeout = TIMEOUT)
    public void isLongCompleted() throws Exception {
        assertEquals(ctrl.isCompleted("32.02", "", WaterSourceReport.WaterType.BT, WaterSourceReport.WaterCondition.C), false);
    }

    @Test(timeout = TIMEOUT)
    public void isTypeCompleted() throws Exception {
        assertEquals(ctrl.isCompleted("32.02", "", null, WaterSourceReport.WaterCondition.C), false);
    }

    @Test(timeout = TIMEOUT)
    public void isConditionCompleted() throws Exception {
        assertEquals(ctrl.isCompleted("32.02", "", WaterSourceReport.WaterType.BT, null), false);
    }


}