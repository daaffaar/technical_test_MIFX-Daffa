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
    
   
    def userList = json.data
    
    
    userList.eachWithIndex { user, index ->
        KeywordUtil.logInfo("Checking User at index: ${index}")
        
        
        assert user.id != null : "ID is null at index ${index}"
        assert user.email != null : "Email is null at index ${index}"
        assert user.first_name != null : "First Name is null at index ${index}"
        assert user.last_name != null : "Last Name is null at index ${index}"
        
        KeywordUtil.logInfo("User [${user.first_name}] - Data integrity PASSED")
    }
    
    KeywordUtil.logInfo("SUCCESS: All users in the list have complete data")

	} else {
    KeywordUtil.markFailed("FAILED: Expected 200 but got ${statusCode}")
	}
