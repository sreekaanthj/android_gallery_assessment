package com.dazn.assessment.gallery.data.datasource

import android.content.Context
import android.util.Log
import com.dazn.assessment.gallery.data.model.GalleryDataResult
import com.dazn.assessment.gallery.data.model.ImageInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class LocalJsonGalleryDataSource @Inject constructor(@ApplicationContext private val context:Context,@Named("localJsonFileName") private val jsonFileName:String) :GalleryDataSource {
    private val TAG = "LocalJsonGalleryDataSou"

    override suspend fun getImages(): GalleryDataResult<List<ImageInfo>> {
        return try {
            Log.i(TAG, "getImages: loading content from file $jsonFileName")

            //load json content from file
            val jsonContent = context.assets.open(jsonFileName).bufferedReader().use { it.readText() }

            Log.i(TAG, "getImages: json cotnent available, content len: ${jsonContent.length}")
            // create type token for GSON to parse the typed object
            val listCountryType = object : TypeToken<List<ImageInfo>>() {}.type


            Log.i(TAG, "getImages: parsing content to data objects...")
            // parse json to actual data objects
            val images = Gson().fromJson<List<ImageInfo>>(jsonContent, listCountryType)


            Log.i(TAG, "getImages: parsed data length: ${images.size}")
            /*
            sorting.. as the date format is YYYY-mm-dd format, default string sorting algorithm works as expected.
            And we need latest items first, so ordering in descending.
             */
            val sortedImageInfos = images.sortedByDescending { it.date }

            Log.i(
                TAG,
                "getImages: sorting images done. first elelement date: ${sortedImageInfos[0].date}"
            )
            GalleryDataResult.Success(sortedImageInfos)
        }catch (e:IOException){
            Log.w(TAG, "getImages: failed to parsing json : ${e.message}")
           GalleryDataResult.Error(Exception("Data Parsing Error: ${e.message}",e))
        }
    }
}