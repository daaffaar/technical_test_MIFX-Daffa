import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonSlurper as JsonSlurper         

def response = WS.sendRequest(findTestObject("Object Repository/Postman/GET_Users", [
	('page') : '2',
	('apiKey') : 'reqres_5892c2e69cce4e489bf1f74b687e0db2'
]))

int statusCode = response.getStatusCode()

if (statusCode == 200) {
	String remark = "SECURITY BUG: API still returns 200 OK with incorrect x-api-key"
	
	WS.comment(remark)
	KeywordUtil.markFailed(remark)
	
} else if (statusCode == 403) {

    def json = new JsonSlurper().parseText(response.getResponseText())
    

    String expectedError = "invalid_api_key"
    String expectedMessage = "This API key is not recognized or has been revoked."
    

    String actualError = json.error
    String actualMessage = json.message
    

    boolean isErrorMatch = WS.verifyMatch(actualError, expectedError, false)
    boolean isMessageMatch = WS.verifyMatch(actualMessage, expectedMessage, false)
    
    if (isErrorMatch && isMessageMatch) {
        WS.comment("PASSED: Error code and Message match documentation.")
    } else {
        KeywordUtil.markFailed("MISMATCH: Expected [${expectedError}] but got [${actualError}]")
    }
	
} else {
	KeywordUtil.markFailed("Unexpected Status Code: " + statusCode)
}