/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abn.futuretransactions;

import com.abn.futuretransactions.utils.SystemFieldPosition;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.abn.futuretransactions.utils.ParseStringUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author abhi
 */
public class FutureTransactions {
    private static final Logger LOGMSG = LogManager.getLogger(FutureTransactions.class);
    private static final char COMMA_SEPARATOR = ',';

    private String inFilePath;
    private String outFilePath;

    public FutureTransactions(String inFilePath, String outFilePath) {
        this.inFilePath = inFilePath;
        this.outFilePath = outFilePath;
    }

    public String getInFilePath() {
        return inFilePath;
    }

    public void setInFilePath(String inFilePath) {
        this.inFilePath = inFilePath;
    }

    public String getOutFilePath() {
        return outFilePath;
    }

    public void setOutFilePath(String outFilePath) {
        this.outFilePath = outFilePath;
    }

    public FutureTransactions() {
    }

    public void parseInputTextFile(File inputFile) {
        LOGMSG.info("Inside parseInputTextFile function");
        try {
            Scanner inputFile_Scanner = new Scanner(inputFile);

            List<HeaderInformation> resultDetailsList = new ArrayList<HeaderInformation>();

            while (inputFile_Scanner.hasNext()) {

                String inputLine = inputFile_Scanner.nextLine();
                String client_type = inputLine.substring(SystemFieldPosition.CLIENT_TYPE_START.getSystemFieldNamePos() - 1, SystemFieldPosition.CLIENT_TYPE_END.getSystemFieldNamePos());
                String client_number = inputLine.substring(SystemFieldPosition.CLIENT_NUMBER_START.getSystemFieldNamePos() - 1, SystemFieldPosition.CLIENT_NUMBER_END.getSystemFieldNamePos());
                String account_number = inputLine.substring(SystemFieldPosition.ACCOUNT_NUMBER_START.getSystemFieldNamePos() - 1, SystemFieldPosition.ACCOUNT_NUMBER_END.getSystemFieldNamePos());
                String subaccount_number = inputLine.substring(SystemFieldPosition.SUBACCOUNT_NUMBER_START.getSystemFieldNamePos() - 1, SystemFieldPosition.SUBACCOUNT_NUMBER_END.getSystemFieldNamePos());
                String client_Info = String.format("%s%s%s%s", client_type, client_number, account_number, subaccount_number);

                String exchange_code = inputLine.substring(SystemFieldPosition.EXCHANGE_CODE_START.getSystemFieldNamePos() - 1, SystemFieldPosition.EXCHANGE_CODE_END.getSystemFieldNamePos());
                String product_group_code = inputLine.substring(SystemFieldPosition.PRODUCT_GROUP_CODE_START.getSystemFieldNamePos() - 1, SystemFieldPosition.PRODUCT_GROUP_CODE_END.getSystemFieldNamePos());
                String symbol = inputLine.substring(SystemFieldPosition.SYMBOL_START.getSystemFieldNamePos() - 1, SystemFieldPosition.SYMBOL_END.getSystemFieldNamePos());
                String expiration_date = inputLine.substring(SystemFieldPosition.EXPIRATION_DATE_START.getSystemFieldNamePos() - 1, SystemFieldPosition.EXPIRATION_DATE_END.getSystemFieldNamePos());

                String product_Info = String.format("%s%s%s%s", exchange_code, product_group_code, symbol, expiration_date);

                String quantity_long = inputLine.substring(SystemFieldPosition.QUANTITY_LONG_START.getSystemFieldNamePos() - 1, SystemFieldPosition.QUANTITY_LONG_END.getSystemFieldNamePos());
                String quantity_short = inputLine.substring(SystemFieldPosition.QUANTITY_SHORT_START.getSystemFieldNamePos() - 1, SystemFieldPosition.QUANTITY_SHORT_END.getSystemFieldNamePos());

                int total_trnx_amt_int = Integer.parseInt(quantity_long) - Integer.parseInt(quantity_short);
                String total_trnx_amount = String.valueOf(total_trnx_amt_int);
                resultDetailsList.add(new HeaderInformation(client_Info, product_Info, total_trnx_amount));
                GenerateOutputreport(resultDetailsList);
            }
            inputFile_Scanner.close();
        } catch (Exception Ex) {
            LOGMSG.error(Ex.getMessage());
        }
        LOGMSG.info("** Output file created successfully - " + getOutFilePath());
    }


    public void GenerateOutputreport(List<HeaderInformation> reportDetails) {
        LOGMSG.info("Inside GenerateOutputreport function");
        if (reportDetails == null || reportDetails.isEmpty()) {
            LOGMSG.error("Failed to generate report - Empty header report");
        }

        File reportFile;
        try {
            File dir_temp = new File(getOutFilePath());
            String parentPath = dir_temp.getAbsoluteFile().getParent();
            File directory_Output  = new File(parentPath);
            if (!directory_Output.exists()) {
                if (!directory_Output.mkdir()) {
                    LOGMSG.error("failed to create directory for output csv file generation ");
                    return;
                }
            }

            reportFile = new File(getOutFilePath());
            reportFile.createNewFile();
            try (BufferedWriter output = new BufferedWriter(new FileWriter(reportFile))) {

                output.append(HeaderInformation.getCVSHeader());
                output.newLine();

                for (HeaderInformation r : reportDetails) {
                    String row = r.getCSV();
                    output.append(row);
                    output.newLine();
                }
            }

        } catch (Exception e) {
            LOGMSG.error(e.getMessage());
            return;
        }
    }

   private static class HeaderInformation {
       String client_Info;
       String product_Info;
       String total_trnx_amount;

       public HeaderInformation(String info_Client, String info_Product, String amount) {
           client_Info = info_Client;
           product_Info = info_Product;
           total_trnx_amount = amount;
       }


       public static String getCVSHeader() {
           LOGMSG.info("inside getCVSHeader function");
           String cvsHeader = "CLIENT_INFORMATION" + COMMA_SEPARATOR + "PRODUCT_INFORMATION" + COMMA_SEPARATOR + "TOTAL_TRANSACTION_AMOUNT";
           return cvsHeader;
       }

       public String getCSV() throws Exception {
           LOGMSG.info("inside getCSV function");
           return ParseStringUtil.concatWithCommas(true, true, ParseStringUtil.formatNullToEmpty(client_Info), ParseStringUtil.formatNullToEmpty(product_Info), ParseStringUtil.formatNullToEmpty(total_trnx_amount));
       }

    }

    public static void main(String[] args) {
        LOGMSG.info("Starting the Application");
        FutureTransactions futureTransactions = new FutureTransactions("InputFile_Folder/input.txt", "OutputFile_Folder/Output.csv");
        File file = new File(futureTransactions.getInFilePath());
        futureTransactions.parseInputTextFile(file);
        LOGMSG.info("*** Finished running application ***");
    }
}
