package com.abn.junit.test;

import com.abn.futuretransactions.FutureTransactions;
import com.abn.futuretransactions.utils.ParseStringUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author abhi
 */
public class TestFutureTransactionsTest {
    private static final Logger TESTLOGMSG = LogManager.getLogger(FutureTransactions.class);
    @Test
    public void testFilefromResource()
    {
        FutureTransactions testfutureTransactions= new FutureTransactions();
        boolean exists = true;
        File file = new File("InputFile_Folder/input.txt");
        String strFilename = file.getAbsolutePath();
        assertTrue(String.valueOf(file.exists()), exists);
    }

    @Test
    public void testStringUtilfunctions()
    {
        ParseStringUtil strUtil = new ParseStringUtil();
        String strIn = "CL  432100020001,SGX FUNK    20100910,89895656433";
        String str = ParseStringUtil.concatWithCommas(true, true, ParseStringUtil.formatNullToEmpty("CL  432100020001"), ParseStringUtil.formatNullToEmpty("SGX FUNK    20100910"), ParseStringUtil.formatNullToEmpty("89895656433"));
        assertEquals(strIn, str);
        String strInput = "CL  2333,SGX erwerwerw    2010rtt0910,8989565rrr6433";
        String strresult = ParseStringUtil.concatWithCommas(true, true, ParseStringUtil.formatNullToEmpty("CL  2333"), ParseStringUtil.formatNullToEmpty("SGX erwerwerw    2010rtt0910"), ParseStringUtil.formatNullToEmpty("8989565rrr6433"));
        assertEquals(strInput, strresult);
    }
    @Test
    public void testParseTestFile()
    {
        TESTLOGMSG.info("Start the function testParseTestFile ");
        FutureTransactions testfutureTransactions = new FutureTransactions("src/test/resources/input/TestInput.txt", "src/test/resources/output/TestOutput.csv");
        File testfile = new File(testfutureTransactions.getInFilePath());
        testfutureTransactions.parseInputTextFile(testfile);
        TESTLOGMSG.info("Completed testParseTestFile function.  ");

        File outfile = new File(testfutureTransactions.getOutFilePath());
        assertEquals(outfile.exists(), true);
        TESTLOGMSG.info("-TEST- Output test csv file created succesfully.  ");
    }
}