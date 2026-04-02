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
    

    def requiredFields = ['id', 'email', 'first_name', 'last_name', 'avatar']


    userList.eachWithIndex { user, index ->
        KeywordUtil.logInfo("Validating schema for User at index [${index}]")
        

        requiredFields.each { field ->

            if (user.containsKey(field)) {
                KeywordUtil.logInfo("Field [${field}] exists.")
            } else {
                KeywordUtil.markFailed("SCHEMA ERROR: User at index [${index}] is missing field: [${field}]")
            }
        }
    }
    
    KeywordUtil.logInfo("SUCCESS: All required fields are present in the response.")

} else {
    KeywordUtil.markFailed("FAILED: Expected 200 but got ${statusCode}")
}