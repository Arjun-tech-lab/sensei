package com.sensei.encore.test.ui;

import com.sensei.encore.test.utils.FileLocatorUtils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class ProductUITests extends AbstractUITests {

    @Test
    public void testCreateProduct() {
        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/products.csv"));
        List<ProductFormInputDto> products = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String[] dataFields = Arrays.copyOfRange(fields, 0, fields.length);
            ProductFormInputDto product = ProductFormInputDto.fromVerificationFields(dataFields);
            if (product != null) {
                products.add(product);
            }
        }
        for(ProductFormInputDto product : products) {
            page.navigate(BASE_URL + "#/loans/loan-products?type=create");
            page.getByLabel("Product Code").fill(product.getProductCode());
            page.getByLabel("Currency").selectOption(product.getCurrency());
            page.getByLabel("Amount From").fill(product.getAmountFrom());
            page.getByLabel("Amount To").fill(product.getAmountTo());
            page.getByLabel("Tenure From").fill(product.getTenureFrom());
            page.getByLabel("Tenure To").fill(product.getTenureTo());
            page.getByLabel("Frequency Unit").selectOption(product.getFrequencyUnit());
            page.getByTestId("branchSetCode").click();
            page.getByText(product.getBranchSetCode(),new Page.GetByTextOptions().setExact(true)).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed"))
                    .click();

            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Profile")).click();
            Locator profileTabPanel = page.getByRole(AriaRole.TABPANEL, new Page.GetByRoleOptions().setName("Profile"));
            profileTabPanel.getByLabel("Account ID Code").fill(product.getAccountIdCode());
            profileTabPanel.getByLabel("Category").selectOption(product.getCategory());
            profileTabPanel.getByLabel("Account Id Generation Rule Code").selectOption(product.getAccountIdGenerationRuleCode());

            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Loan Terms")).click();
            Locator loanTermsTabPanel = page.getByRole(AriaRole.TABPANEL, new Page.GetByRoleOptions().setName("Loan Terms"));
            loanTermsTabPanel.getByLabel("Normal Interest Rate Basis").selectOption(product.getNormalInterestRateBasis());
            loanTermsTabPanel.getByLabel("Penal Interest Rate Basis").selectOption(product.getPenalInterestRateBasis());

            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Loan GL")).click();
            Locator loanGlTabPanel = page.getByRole(AriaRole.TABPANEL, new Page.GetByRoleOptions().setName("Loan GL"));

            Locator singleSelectMenu= page.getByTestId("singleSelectMenu");
            loanGlTabPanel.getByTestId("interestReceivableAccountCode").click();
            singleSelectMenu.getByText(product.getInterestReceivableAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("interestIncomeAccountCode").click();
            singleSelectMenu.getByText(product.getInterestIncomeAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("interestAccruedAccountCode").click();
            singleSelectMenu.getByText(product.getInterestAccruedAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("interestSuspenseAccountCode").click();
            singleSelectMenu.getByText(product.getInterestSuspenseAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("normalInterestWriteOffAccountCode").click();
            singleSelectMenu.getByText(product.getNormalInterestWriteOffAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("normalInterestWaiverAccountCode").click();
            singleSelectMenu.getByText(product.getNormalInterestWaiverAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("normalInterestIncomeBookedNpaAccountCode").click();
            singleSelectMenu.getByText(product.getNormalInterestIncomeBookedNpaAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("normalInterestIncomeRepaidNpaAccountCode").click();
            singleSelectMenu.getByText(product.getNormalInterestIncomeRepaidNpaAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("interestTdsAccountCode").click();
            singleSelectMenu.getByText(product.getInterestTdsAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();


            loanGlTabPanel.getByTestId("penalInterestReceivableAccountCode").click();
            singleSelectMenu.getByText(product.getPenalInterestReceivableAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("penalInterestIncomeAccountCode").click();
            singleSelectMenu.getByText(product.getPenalInterestIncomeAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("penalInterestAccruedAccountCode").click();
            singleSelectMenu.getByText(product.getPenalInterestAccruedAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("penalInterestSuspenseAccountCode").click();
            singleSelectMenu.getByText(product.getPenalInterestSuspenseAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("penalInterestWriteOffAccountCode").click();
            singleSelectMenu.getByText(product.getPenalInterestWriteOffAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("penalInterestWaiverAccountCode").click();
            singleSelectMenu.getByText(product.getPenalInterestWaiverAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("penalInterestIncomeBookedNpaAccountCode").click();
            singleSelectMenu.getByText(product.getPenalInterestIncomeBookedNpaAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("penalInterestIncomeRepaidNpaAccountCode").click();
            singleSelectMenu.getByText(product.getPenalInterestIncomeRepaidNpaAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();


            loanGlTabPanel.getByTestId("additionalInterestReceivableAccountCode").click();
            singleSelectMenu.getByText(product.getAdditionalInterestReceivableAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("additionalInterestIncomeAccountCode").click();
            singleSelectMenu.getByText(product.getAdditionalInterestIncomeAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("additionalInterestAccruedAccountCode").click();
            singleSelectMenu.getByText(product.getAdditionalInterestAccruedAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("additionalInterestSuspenseAccountCode").click();
            singleSelectMenu.getByText(product.getAdditionalInterestSuspenseAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("additionalInterestWriteOffAccountCode").click();
            singleSelectMenu.getByText(product.getAdditionalInterestWriteOffAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("additionalInterestWaiverAccountCode").click();
            singleSelectMenu.getByText(product.getAdditionalInterestWaiverAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("additionalInterestIncomeBookedNpaAccountCode").click();
            singleSelectMenu.getByText(product.getAdditionalInterestIncomeBookedNpaAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("additionalInterestIncomeRepaidNpaAccountCode").click();
            singleSelectMenu.getByText(product.getAdditionalInterestIncomeRepaidNpaAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();


            loanGlTabPanel.getByTestId("loanLossProvisionReserveAccountCode").click();
            singleSelectMenu.getByText(product.getLoanLossProvisionReserveAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("loanLossProvisionStdExpenseAccountCode").click();
            singleSelectMenu.getByText(product.getLoanLossProvisionStdExpenseAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("loanLossProvisionStdIncomeAccountCode").click();
            singleSelectMenu.getByText(product.getLoanLossProvisionStdIncomeAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("loanLossProvisionNpaExpenseAccountCode").click();
            singleSelectMenu.getByText(product.getLoanLossProvisionNpaExpenseAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("loanLossProvisionNpaIncomeAccountCode").click();
            singleSelectMenu.getByText(product.getLoanLossProvisionNpaIncomeAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();


            loanGlTabPanel.getByTestId("tax1AccountCode").click();
            singleSelectMenu.getByText(product.getTax1AccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("tax2AccountCode").click();
            singleSelectMenu.getByText(product.getTax2AccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("tax3AccountCode").click();
            singleSelectMenu.getByText(product.getTax3AccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("tax4AccountCode").click();
            singleSelectMenu.getByText(product.getTax4AccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();



            loanGlTabPanel.getByTestId("forfeitureAccountCode").click();
            singleSelectMenu.getByText(product.getForfeitureAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("waiverAccountCode").click();
            singleSelectMenu.getByText(product.getWaiverAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("writeOffRecoveryAccountCode").click();
            singleSelectMenu.getByText(product.getWriteOffRecoveryAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();


            loanGlTabPanel.getByTestId("principalWriteOffAccountCode").click();
            singleSelectMenu.getByText(product.getPrincipalWriteOffAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("principalContraAccountCode").click();
            singleSelectMenu.getByText(product.getPrincipalContraAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("principalReceivableAccountCode").click();
            singleSelectMenu.getByText(product.getPrincipalReceivableAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("principalControlAccountCode").click();
            singleSelectMenu.getByText(product.getPrincipalControlAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();


            loanGlTabPanel.getByLabel("GL Sub Head").selectOption(product.getGlSubHead());

            loanGlTabPanel.getByTestId("securityDepositAccountCode").click();
            singleSelectMenu.getByText(product.getSecurityDepositAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();

            loanGlTabPanel.getByTestId("securityDepositAdvanceInterestAccountCode").click();
            singleSelectMenu.getByText(product.getSecurityDepositAdvanceInterestAccountCode(),
                    new Locator.GetByTextOptions().setExact(true)).click();


            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
        }
        page.pause();
    }
}
