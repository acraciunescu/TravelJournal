package andreea.my.traveljournal.fragment_trips;

public class Trip {
    private String mUserId;

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    private String mSeason;
    private String mLocationName;
    private String mPicture;
    private double mPrice;
    private double mRating;
    private boolean mBookmark;

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public double getmRating() {
        return mRating;
    }

    public void setmRating(double mRating) {
        this.mRating = mRating;
    }

    public boolean ismBookmark() {
        return mBookmark;
    }

    public void setmBookmark(boolean mBookmark) {
        this.mBookmark = mBookmark;
    }

    public String getmSeason() {
        return mSeason;
    }

    public void setmSeason(String mSeason) {
        this.mSeason = mSeason;
    }

    public String getmLocationName() {
        return mLocationName;
    }

    public void setmLocationName(String mLocationName) {
        this.mLocationName = mLocationName;
    }

    public String getmPicture() {
        return mPicture;
    }

    public void setmPicture(String mPicture) {
        this.mPicture = mPicture;
    }

    public Trip(String mId, String mSeason, String mLocationName, String mPicture, double mPrice, double mRating, boolean mBookmark) {
        this.mUserId = mId;
        this.mSeason = mSeason;
        this.mLocationName = mLocationName;
        this.mPicture = mPicture;
        this.mPrice = mPrice;
        this.mRating = mRating;
        this.mBookmark = mBookmark;
    }
}
