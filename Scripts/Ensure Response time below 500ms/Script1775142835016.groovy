import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonSlurper as JsonSlurper

def response = WS.sendRequest(findTestObject("Object Repository/Postman/GET_Users", [
    ('page') : '2',
    ('apiKey') : 'reqres_5892c2e69cce4e489bf1f74b687e0db3'
]))

int statusCode = response.getStatusCode()
long responseTime = response.getElapsedTime()


if (statusCode == 200) {
    KeywordUtil.logInfo("SUCCESS: Status code is 200 OK")
    
    
    if (responseTime < 500) {
        KeywordUtil.logInfo("PERFORMANCE PASSED: Response time is fast (${responseTime} ms)")
    } else {
        KeywordUtil.markFailed("PERFORMANCE FAILED: API too slow! Actual: ${responseTime} ms (Limit: 500 ms)")
    }
    
	} else {

    KeywordUtil.markFailed("FAILED: Unexpected Status Code [${statusCode}]")
	} 

	WS.comment("Final Result: Checked Status, Time (${responseTime}ms)")