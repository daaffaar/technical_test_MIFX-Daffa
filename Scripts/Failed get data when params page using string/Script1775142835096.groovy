import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

def response = WS.sendRequest(findTestObject("Object Repository/Postman/GET_Users", [
    ('page') : 'abc',
    ('apiKey') : 'reqres_5892c2e69cce4e489bf1f74b687e0db3'
]))

int statusCode = response.getStatusCode()

if (statusCode == 400) {
    WS.verifyResponseStatusCode(response, 400)
    KeywordUtil.logInfo("PASSED: API rejected string input with 400 Bad Request as expected")
    
} else if (statusCode == 200) {
    String errorMsg = "API returns 200 OK for 'abc' page. Should be 400 Bad Request!"
    WS.comment(errorMsg)
    KeywordUtil.markFailed(errorMsg)

} 

WS.comment("Final Result: Checked Strict Validation for Page Parameter.")