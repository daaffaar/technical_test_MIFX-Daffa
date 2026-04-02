import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonSlurper as JsonSlurper

// 1. Hit API
def response = WS.sendRequest(findTestObject("Object Repository/Postman/GET_Users", [
    ('page') : '999',
    ('apiKey') : 'reqres_5892c2e69cce4e489bf1f74b687e0db3'
]))

int statusCode = response.getStatusCode()

if (statusCode == 200) {
    def json = new JsonSlurper().parseText(response.getResponseText())
    def userList = json.data
    
    if (userList instanceof List) {
        int listSize = userList.size()
        
        // 4. Cek apakah Array-nya Kosong
        if (listSize == 0) {
            KeywordUtil.logInfo("PASSED: Response is an empty array as expected")
        }
    }

	} else {
    KeywordUtil.markFailed("Unexpected Status Code: " + statusCode)
	}


	WS.comment("Final Result: Checked for Empty Data Array")