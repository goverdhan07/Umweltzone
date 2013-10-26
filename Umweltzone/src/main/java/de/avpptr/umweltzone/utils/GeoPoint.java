/*
 *  Copyright (C) 2013  Tobias Preuss, Peter Vasil
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.avpptr.umweltzone.utils;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class GeoPoint {

    private static final DecimalFormat df =
            new DecimalFormat("@@@@@", new DecimalFormatSymbols(Locale.US));
    protected double mLatitude = Double.NaN;
    protected double mLongitude = Double.NaN;

    private final double MAX_LATITUDE = 90.0;
    private final double MIN_LATITUDE = -90.0;
    private final double MAX_LONGITUDE = 180.0;
    private final double MIN_LONGITUDE = -180.0;

    public GeoPoint() {
        // Required by Jackson to de-serialize JSON content
    }

    public GeoPoint(int latitudeE6, int longitudeE6) {
        mLatitude = latitudeE6 / 1e6;
        mLongitude = longitudeE6 / 1e6;
    }

    public GeoPoint(LatLng latLng) {
        mLatitude = latLng.latitude;
        mLongitude = latLng.longitude;
    }

    public GeoPoint(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    // Required by Jackson to de-serialize JSON content
    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    // Required by Jackson to de-serialize JSON content
    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public boolean isValid() {
        return (mLatitude > MIN_LATITUDE && mLatitude <= MAX_LATITUDE
                && mLongitude > MIN_LONGITUDE && mLongitude <= MAX_LONGITUDE);
    }

    public LatLng toLatLng() {
        return new LatLng(mLatitude, mLongitude);
    }

    @Override
    public String toString() {
        return "latitude = " + df.format(mLatitude) + ", longitude = " + df.format(mLongitude);
    }

}
