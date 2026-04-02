import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonSlurper as JsonSlurper


def response = WS.sendRequest(findTestObject("Object Repository/Postman/GET_Users", [
    ('page') : '2',
    ('apiKey') : 'reqres_5892c2e69cce4e489bf1f74b687e0db3'
]))

int statusCode = response.getStatusCode()

	if (statusCode == 200) {
   
    def json = new JsonSlurper().parseText(response.getResponseText())
    
    int expectedCount = json.per_page
    
    int actualCount = json.data.size()
    
    KeywordUtil.logInfo("Expected count from 'per_page': " + expectedCount)
    KeywordUtil.logInfo("Actual count from array 'data': " + actualCount)


    if (actualCount == expectedCount) {
        KeywordUtil.logInfo("PASSED: Data count matches 'per_page' value.")
        WS.verifyEqual(actualCount, expectedCount)
    } else {

        KeywordUtil.markFailed("MISSMATCH: 'per_page' is ${expectedCount}, but array contains ${actualCount} items")
    }

	} else {
    KeywordUtil.markFailed("FAILED: Unexpected Status Code: " + statusCode)
	}

	WS.comment("Final Result: Checked Data Consistency (per_page vs array size)")