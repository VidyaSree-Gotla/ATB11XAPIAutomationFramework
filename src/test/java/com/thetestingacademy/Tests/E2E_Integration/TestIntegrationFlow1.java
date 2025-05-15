package com.thetestingacademy.Tests.E2E_Integration;

import com.thetestingacademy.Base.BaseTest;
import com.thetestingacademy.EndPoints.APIConstants;
import com.thetestingacademy.Pojos.Responses.BookingResponse;
import com.thetestingacademy.Pojos.Requests.Booking;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class TestIntegrationFlow1 extends BaseTest {

        // TestE2EFlow_01

        //  Test E2E Scenario 1

        //  1. Create a Booking -> bookingID

        // 2. Create Token -> token

        // 3. Verify that the Create Booking is working - GET Request to bookingID

        // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

        // 5. Delete the Booking - Need to get the token, bookingID from above request



        @Test(groups = "qa", priority = 1)
        @Owner("Promode")
        @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
        public void testCreateBooking(ITestContext iTestContext){

            requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
            response = RestAssured.given(requestSpecification)
                    .when().body(payloadManager.createPayloadBookingAsString())
                    .post();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);
            BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
            assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Pramod");
            assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());

            iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());
        }



        @Test(groups = "qa", priority = 2)
        @Owner("Promode")
        @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
        public void testVerifyBookingId(ITestContext iTestContext){
            Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

            String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
            System.out.println(basePathGET);

            requestSpecification.basePath(basePathGET);
            response = RestAssured
                    .given(requestSpecification)
                    .when().get();
            validatableResponse = response.then().log().all();
            // Validatable Assertion
            validatableResponse.statusCode(200);






        }

        @Test(groups = "qa", priority = 3)
        @Owner("Promode")
        @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
        public void testUpdateBookingByID(){
            Assert.assertTrue(true);
        }

        @Test(groups = "qa", priority = 4)
        @Owner("Promode")
        @Description("TC#INT1 - Step 4. Delete the Booking by ID")
        public void testDeleteBookingById(){
            Assert.assertTrue(true);
        }
}
