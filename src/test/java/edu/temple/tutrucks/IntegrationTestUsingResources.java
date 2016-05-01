/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author nickdellosa
 */
public class IntegrationTestUsingResources {

    @BeforeClass
    public static void setupClass() {
        IntegrationTestResources.acquire();
    }

    @AfterClass
    public static void tearDownClass() {
        IntegrationTestResources.release();
    }

    public static class IntegrationTestResources {

        private static final int TESTCOUNT = 3;
        private static int currentCount = 0;
        private static boolean setup = false;

        public static final String EMAIL = "testaccount@test.com";
        public static final String PASSWORD = "password";
        private static User testUser;

        protected static synchronized void acquire() {
            if (!setup) {
                testUser = User.createUser(EMAIL, PASSWORD, false, null, null, null);
                setup = true;
            }
        }

        protected static synchronized void release() {
            currentCount++;
            if (currentCount == TESTCOUNT) {
                testUser.delete();
                setup = false;
            }
        }
        
        protected static User getTestUser() {
            if (setup) {
                return testUser;
            } else {
                acquire();
                return getTestUser();
            }
        }
    }
}
