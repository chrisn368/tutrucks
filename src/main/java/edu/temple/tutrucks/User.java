package edu.temple.tutrucks;
// Generated Feb 15, 2016 6:30:46 PM by Hibernate Tools 4.3.1

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.hibernate.Query;
import org.hibernate.Session;



/**
 * User generated by hbm2java
 * 
 * Represents a user of the TUTrucks application. Its mapping file is User.hbm.xml
 * 
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 */
public class User implements java.io.Serializable {

    private static final Random SALTER = new java.security.SecureRandom();
    private static final String APP_ID = "1272882256060359";
    private static final String APP_SECRET = "f45bb012fb2097e5a7aa53fded1c2383";
    private static final String TOKEN_EXTENDER_URL = "www.facebook.com/oauth/access_token";
    private static final int TOKEN_LENGTH = 1024;

    private int id;
    private String userEmail;
    private String passWord;
    private boolean fbLink;
    private String avatar;
    private List<TruckReview> truckReviews = new ArrayList<>();
    private List<ItemReview> itemReviews = new ArrayList<>();
    private String displayName;
    private Permissions permissions;
    private byte[] salt;
    private String facebookID;

    /**
     * Empty constructor required by Hibernate
     */
    public User() {
    }

    /**
     * Returns the ID of this user. Required by Hibernate
     *
     * @return the ID of this user
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the ID of this user. Required by Hibernate
     *
     * @param id the ID of this user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the email this user registered with. Required by Hibernate
     *
     * @return this user's email address
     */
    public String getUserEmail() {
        return this.userEmail;
    }

    /**
     * Sets the email address of this user. Required by Hibernate
     *
     * @param userEmail this user's email address
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Returns the salted and hashed user password stored in the database.
     * Required by Hibernate
     *
     * @return this user's password, properly salted and hashed
     */
    public String getPassWord() {
        return this.passWord;
    }

    /**
     * Sets this user's password. It is assumed that the string passed to this
     * function has already been properly salted and hashed. Required by
     * Hibernate
     *
     * @param passWord this user's password, properly salted and hashed
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * Returns a boolean value reflecting whether this user signed up via
     * Facebook. Required by Hibernate
     *
     * @return true if the user signed up via Facebook, otherwise false
     */
    public boolean isFbLink() {
        return this.fbLink;
    }

    /**
     * Sets the boolean value reflecting whether this user signed up via
     * Facebook. Required by Hibernate
     *
     * @param fbLink boolean value reflecting whether this user signed up via
     * Facebook
     */
    public void setFbLink(boolean fbLink) {
        this.fbLink = fbLink;
    }

    /**
     * Returns a link to this user's avatar. Required by Hibernate
     * @return a link to this user's avatar
     */
    public String getAvatar() {
        return this.avatar;
    }
    /**
     * Sets the link to this user's avatar. Required by Hibernate
     * @param avatar the link to this user's avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    /**
     * Returns a list of reviews for trucks written by this user. Required by Hibernate
     * @return the list of reviews for trucks written by this user
     */
    public List getTruckReviews() {
        return this.truckReviews;
    }
    /**
     * Returns a list of reviews for items written by this user. Required by Hibernate
     * @return the list of reviews for items written by this user
     */
    public List getItemReviews() {
        return itemReviews;
    }
    /**
     * Adds a review to this user's list of reviews. The user for the review must already be the user whose list the review is being added to. Required by Hibernate
     * @param r the review to add to this user's list of reviews
     */
    public void addReview(Review r) {
        if (!r.getUser().equals(this)) {
            //error handling
            return;
        }
        if (r.getClass() == TruckReview.class)
            truckReviews.add((TruckReview)r);
        else
            itemReviews.add((ItemReview)r);
    }
    /**
     * Sets the list of reviews for trucks written by this user. Required by Hibernate
     * @param truckReviews the list of reviews for trucks written by this user
     */
    public void setTruckReviews(List<TruckReview> truckReviews) {
        this.truckReviews.clear();
        this.truckReviews.addAll(truckReviews);
    }
    /**
     * Sets the list of reviews for items written by this user. Required by Hibernate
     * @param itemReviews the list of reviews for items written by this user
     */
    public void setItemReviews(List<ItemReview> itemReviews) {
        this.itemReviews.clear();
        this.itemReviews.addAll(itemReviews);
    }
    /**
     * Returns the display name for this user. Required by Hibernate
     * @return the display name for this user
     */
    public String getDisplayName() {
        return displayName;
    }
    /**
     * Sets the display name for this user. Required by Hibernate
     * @param displayName the display name for this user
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    /**
     * Returns the level of permissions available to this user. Required by Hibernate
     * @return the level of permissions available to this user
     */
    public Permissions getPermissions() {
        return permissions;
    }
    /**
     * Sets the level of permissions available to this user. Required by Hibernate
     * @param permissions the level of permissions available to this user
     */
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }
    
    public void changeDisplayName(String newDisplayName) {
        this.setDisplayName(newDisplayName);
        this.save();
    }
    
    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
    
    public String getFacebookID() {
        return facebookID;
    }
    
    public void setFacebookID(String fbID) {
        this.facebookID = fbID;
    }
    
    private static byte[] generateSalt() {
        byte[] salt = new byte[16];
        SALTER.nextBytes(salt);
        return salt;
    }
    
    public void changePassword(String newPassword) {
        byte[] newSalt = generateSalt();
        String epass = encryptPassword(newPassword, newSalt);
        this.setSalt(newSalt);
        this.setPassWord(epass);
        this.save();
    }
    
    private static String encryptPassword(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] pwba = password.getBytes(StandardCharsets.UTF_8);
            byte[] saltedPassword = new byte[salt.length + pwba.length];
            for (int i=0; i < saltedPassword.length; i++)
                saltedPassword[i] = (i < 16 ? salt[i] : pwba[i-16]);
            
            byte[] hash = digest.digest(saltedPassword);
            String hashed = new String(hash).replace("'", "\\'").replace("#", "\\#");
            StringBuilder retval = new StringBuilder();
            for (char c : hashed.toCharArray()) {
                if (c < 32)
                    retval.append(c + 32);
                else
                    retval.append(c);
            }
            return String.valueOf(retval);
        } catch (NoSuchAlgorithmException ex) {
            //error handling
            return null;
        }
    }

    public static User validateUser(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q1 = session.createQuery("select u.salt from User u where u.userEmail='" + email + "'");
        byte[] salt;
        try {
            salt = (byte[]) q1.uniqueResult();
            password = encryptPassword(password, salt);
        } catch (ClassCastException cce) {

        }
        try {
            Query q = session.createQuery("from User u where u.userEmail='" + email + "' and u.passWord='" + password + "'");
            User retval = (User) q.uniqueResult();
            session.close();
            return retval;
        } catch (ClassCastException cce) {
            // handle this
            session.close();
            return null;
        }
    }
    
    public static User validateUserFacebook(String email, String fbID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from User u where u.userEmail='" + email + "' and u.fbID='" + fbID + "'");
        try {
            User retval = (User) q.uniqueResult();
            session.close();
            return retval;
        } catch (ClassCastException cce) {
            session.close();
            return null;
        }
    }
    
    public void linkUserFacebook(String fbID) {
        this.setFbLink(true);
        this.setFacebookID(fbID);
        this.save();
    }
    
    public void linkUserFacebook(String fbID, String displayName, String avatar) {
        this.setDisplayName(displayName);
        this.setAvatar(avatar);
        this.linkUserFacebook(fbID);
    }
    /*
    private static String validateFacebookToken(String fbToken) {
        try {
            URL tokenExtender = new URL(TOKEN_EXTENDER_URL);
            HttpsURLConnection con = (HttpsURLConnection) tokenExtender.openConnection();
            String newToken = null;
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            String params = "grant_type=fb_exchange_token&client_id=" + APP_ID + "&client_secret=" + APP_SECRET + "&fb_exchange_token=" + fbToken;
            try (OutputStream stream = con.getOutputStream()) {
                stream.write(params.getBytes());
                stream.flush();
            }
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                byte[] responseToken = new byte[TOKEN_LENGTH];
                try (InputStream in = con.getInputStream()) {
                    in.read(responseToken);
                }
                newToken = new String(responseToken);
            }
            con.disconnect();
            return newToken;
        } catch (IOException ex) {
            
        }
        return null;
    } */
    
    public static User createUser(String email, String password, boolean facebook, String displayName, String fbAvatarURL, String fbID) {
        User user = new User();
        user.setUserEmail(email);
        byte[] salt = generateSalt();
        user.setSalt(salt);
        user.setPassWord(encryptPassword(password, salt));
        user.setFbLink(facebook);
        user.setPermissions(Permissions.PLEB);
        if (facebook) {
            user.setDisplayName(displayName);
            user.setAvatar(fbAvatarURL);
            user.setFacebookID(fbID);
        } else {
            user.setDisplayName(email.substring(0, email.indexOf('@')));
        }
        user.save();
        return validateUser(email, password);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            return this.id == ((User)o).id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.userEmail);
        hash = 83 * hash + Objects.hashCode(this.permissions);
        return hash;
    }
    
    public void save() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(this);
        session.getTransaction().commit();
        session.close();
    }
    
}


