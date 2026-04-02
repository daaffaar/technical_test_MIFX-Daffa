import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonSlurper as JsonSlurper


String inputPage = '2'


def response = WS.sendRequest(findTestObject("Object Repository/Postman/GET_Users", [
	('page') : inputPage,
	('apiKey') : 'reqres_5892c2e69cce4e489bf1f74b687e0db3'
]))

int statusCode = response.getStatusCode()

if (statusCode == 200) {

	def json = new JsonSlurper().parseText(response.getResponseText())
	
	String actualPage = json.page.toString()
	
	KeywordUtil.logInfo("Input Param Page: " + inputPage)
	KeywordUtil.logInfo("Response Field Page: " + actualPage)


	if (actualPage == inputPage) {
		KeywordUtil.logInfo("PASSED: Response page matches the requested parameter")
		WS.verifyEqual(actualPage, inputPage)
	} else {
		String errorMsg = "LOGIC BUG: Requested page ${inputPage} but got ${actualPage} in response!"
		KeywordUtil.markFailed(errorMsg)
	}

} else {
	KeywordUtil.markFailed("FAILED: Unexpected Status Code: " + statusCode)
}

WS.comment("Final Result: Checked Page Parameter Consistency.")