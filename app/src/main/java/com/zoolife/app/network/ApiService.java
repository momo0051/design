package com.zoolife.app.network;

import com.google.gson.JsonObject;
import com.zoolife.app.ResponseModel.AddComment.AddCommentResponseModel;
import com.zoolife.app.ResponseModel.AddDelivery.AddDeliveryResponseModel;
import com.zoolife.app.ResponseModel.AddPost.AddPostResponseModel;
import com.zoolife.app.ResponseModel.AllPost.AllPostResponseModel;
import com.zoolife.app.ResponseModel.Articles.AllArticlesResponseModel;
import com.zoolife.app.ResponseModel.Category.CategoryResponseModel;
import com.zoolife.app.ResponseModel.ChangePassword.ChangePasswordResponseModel;
import com.zoolife.app.ResponseModel.CityNameResponseModel.CityNameResponseModel;
import com.zoolife.app.ResponseModel.FavModel.FavResponseModel;
import com.zoolife.app.ResponseModel.GetFavourites.GetFavouritesResponse;
import com.zoolife.app.ResponseModel.GetPost.GetPostResponseModel;
import com.zoolife.app.ResponseModel.GetUserProfile.GetUserProfileResponseModel;
import com.zoolife.app.ResponseModel.NoDataResponseModel;
import com.zoolife.app.ResponseModel.OTP.OTPResponseModel;
import com.zoolife.app.ResponseModel.SearchPost.SearchResponseModel;
import com.zoolife.app.ResponseModel.ShowComment.ViewCommentsResponseModel;
import com.zoolife.app.ResponseModel.SubCategory.SubCategoryResponseModel;
import com.zoolife.app.ResponseModel.UpdateDeviceInfo.UpdateDeviceInfoResponse;
import com.zoolife.app.ResponseModel.UpdateProfile.UpdateProfileResponseModel;
import com.zoolife.app.ResponseModel.UserPost.UserAllPostResponseModel;
import com.zoolife.app.models.SliderModel;
import com.zoolife.app.models.notification.NotificationModel;
import com.zoolife.app.models.related_ad_home.RelatedAdHomeModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {


    /*@FormUrlEncoded
    @POST("/api/public/auth")
    Call<JsonObject> signIn(
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("password") String password
    );*/
    @FormUrlEncoded
    @POST("public/api/loginapi")
    Call<JsonObject> signIn(
            @Field("phone") String phone,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("public/api/registerapi")
    Call<JsonObject> signUp(
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );


    /*@FormUrlEncoded
    @POST("/api/public/auth")
    Call<OTPResponseModel> otpVerify(
            @Field("pass") String pass,
            @Field("otp") String otp,
            @Field("username") String username
    );
*/
    @FormUrlEncoded
    @POST("public/api/verify_otp")
    Call<OTPResponseModel> otpVerify(
            @Field("phone") String phone,
            @Field("otp") String otp
    );


   /* @FormUrlEncoded
    @POST("/api/public/auth")
    Call<JsonObject> resetPassword(
            @Field("pass") String pass,
            @Field("username") String username
    );*/

    @FormUrlEncoded
    @POST("public/api/reset_password")
    Call<JsonObject> resetPassword(
            @Field("phone") String pass
    );


   /* @FormUrlEncoded
    @POST("/api/public/auth")
    Call<ChangePasswordResponseModel> updatePassword(
            @Field("pass") String pass,
            @Field("password") String password,
            @Field("username") String username
    );*/

    @FormUrlEncoded
    @POST("public/api/update_password")
    Call<ChangePasswordResponseModel> updatePassword(
            @Field("password") String pass,
            @Field("phone") String phone
    );



    /*@FormUrlEncoded
//    @POST("/api/public/category")
    @POST("/api/category")
    Call<CategoryResponseModel> getCategory(
            @Field("pass") String pass

    );*/


    //Checked Working
    @POST("public/api/category")
    Call<CategoryResponseModel> getCategory(


    );


    // @POST("/api/public/home")
    @GET("public/api/itemsapi")
    Call<AllPostResponseModel> getAllPost(

    );

//     @FormUrlEncoded
//     @POST("/api/public/home")
//     Call<AllCategoryPostResponseModel> getPostByCategory(
//             @Field("pass") String pass


//     );


//     @FormUrlEncoded
//     @POST("/api/public/home")
//     Call<SearchResponseModel> getSearch(
//             @Field("pass") String pass,
//             @Field("search") String search

//     );


   /* @FormUrlEncoded
    @POST("/api/public/category")
    Call<SubCategoryResponseModel> getSubCategory(
            @Field("pass") String pass,
            @Field("category_id") int category_id

    );*/


    @FormUrlEncoded
    @POST("public/api/get_sub_category")
    Call<SubCategoryResponseModel> getSubCategory(
            @Field("category_id") int category_id

    );

    @FormUrlEncoded
    @POST("public/api/sliders")
    Call<SliderModel> getSliders(
            @Field("pass") String allSliders
    );

    @FormUrlEncoded
    @POST("public/api/sliders")
    Call<ResponseBody> getSliders1(
            @Field("pass") String allSliders
    );





//     @FormUrlEncoded
//     @POST("/api/public/notification")
//     Call<NotificationsResponseModel> getNotifications(
//             @Field("pass") String pass,
//             @Field("username") String username

//     );


//     @FormUrlEncoded
//     @POST("/api/get_all_notify")
//     Call<NotificationModel> getNotifications(
//             @Field("user_id") String userId
//     );

    @FormUrlEncoded
    //@POST("/api/reportapi")
    @POST("public/api/reportapi")
    Call<AddPostResponseModel> reportApi(
            @Field("pass") String pass,
            @Field("ads_id") String adsId,
            @Field("user_id") String userId,
            @Field("content") String content
    );


//     @Multipart
//     @POST("/api/public/item")
//     Call<AddPostResponseModel> addPost(
//             @Query("pass") String pass,
//             @Query("username") String username,
//             @Query("location") String location,
//             @Query("title") String title,
//             @Query("description") String description,
//             @Query("category") int category,
//             @Query("sub_category") int sub_category,
//             @Query("showComments") int showComments,
//             @Query("showPhone") int showPhone,
//             @Query("showMessage") int showMessage,
//             @Part MultipartBody.Part MediaFile
//     );

    //@POST("/api/public/item")
    @POST("public/api/add_post")
    Call<AddPostResponseModel> addPost1(
            @Body RequestBody file
    );


//     @POST("/api/sliders")
//     Call<RelatedAdHomeModel> getRelatedAdds(
//             @Body RequestBody file
//     );


    @Multipart
    //@POST("/api/public/home")
    @POST("public/api/home")
    Call<String> uploadImage(
            @Query("pass") String pass,
            @Query("username") String username,
            @Part("images[]") String image,
            @Part MultipartBody.Part MediaFile
    );


//     @FormUrlEncoded
//     @POST("/api/public/item")
//     Call<UserAllPostResponseModel> getAllUserPost(
//             @Field("pass") String pass,
//             @Field("username") String username

//     );


//     @FormUrlEncoded
//     @POST("/api/public/user")
//     Call<UpdateProfileResponseModel> updateProfile(
//             @Field("pass") String pass,
//             @Field("username") String username,
//             @Field("surname") String surname,
//             @Field("fullname") String fullname,
//             @Field("language") String language,
//             @Field("bYear") String bYear,
//             @Field("bMonth") String bMonth,
//             @Field("bDay") String bDay,
//             @Field("country") String country,
//             @Field("country_id") int country_id,
//             @Field("city") String city,
//             @Field("city_id") int city_id

//     );

//     @FormUrlEncoded
//     @POST("/api/public/user")
//     Call<GetUserProfileResponseModel> getProfile(
//             @Field("pass") String pass,
//             @Field("username") String username
//     );


//     @FormUrlEncoded
//     @POST("/api/public/home")
//     Call<UpdateDeviceInfoResponse> updateDeviceInfo(
//             @Field("pass") String pass,
//             @Field("user_id") String userId,
//             @Field("device_token") String deviceToken,
//             @Field("device_type") String deviceType

//     );


//     @FormUrlEncoded
//     @POST("/api/public/comments")
//     Call<AddCommentResponseModel> addComment(
//             @Field("pass") String pass,
//             @Field("userId") int userId,
//             @Field("itemId") int itemId,
//             @Field("message") String message

//     );

    @FormUrlEncoded
    @POST("public/api/add_comments")
    Call<AddCommentResponseModel> addComment(
            @Field("user_id") int userId,
            @Field("id") int id,
            @Field("message") String message

    );

    @FormUrlEncoded
    @POST("public/api/delete_comment")
    Call<NoDataResponseModel> deleteComment(
            @Field("itemId") int itemId,
            @Field("userId") int userId,
            @Field("commentId") int commentId
    );

    @FormUrlEncoded
    @POST("public/api/list_comments_by_item")
    Call<ViewCommentsResponseModel> listCommentByItem(
            @Field("id") String itemId
    );

    @FormUrlEncoded
    @POST("public/api/delete_favorites")
    Call<NoDataResponseModel> deleteFavorites(
            @Field("favoriteId") int favoriteId
    );

    @FormUrlEncoded
    @POST("public/api/favoruit_item")
    Call<FavResponseModel> favoruitItem(
            @Field("user_id") String userId,
            @Field("id") int Itemid,
            @Field("status") int status
    );

    @FormUrlEncoded
    @POST("public/api/fav_item_by_user")
    Call<GetFavouritesResponse> favItembyUser(
            @Field("user_id") String userId
    );

    @FormUrlEncoded
    @POST("public/api/favoruit_list_by_item")
    Call<NoDataResponseModel> favoruitListbyItem(
            @Field("user_id") int userId,
            @Field("id") int itemId
    );

    @FormUrlEncoded
    @POST("public/api/list_likes")
    Call<NoDataResponseModel> listLikes(
            @Field("phone") String phone,
            @Field("fromUserId") int fromUserId,
            @Field("itemId") int itemId
    );

    @FormUrlEncoded
    @POST("public/api/like_item")
    Call<NoDataResponseModel> likeItem(
            @Field("id") int id,
            @Field("user_id") int userId
    );

    @FormUrlEncoded
    @POST("public/api/delete_like_item")
    Call<NoDataResponseModel> deleteLikeItem(
            @Field("phone") String phone,
            @Field("fromUserId") int fromUserId,
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("public/api/abuse_item")
    Call<NoDataResponseModel> abuseItem(
            @Field("user_id") int userId,
            @Field("itemId") int itemId
    );

    @FormUrlEncoded
    @POST("public/api/list_abused_items")
    Call<NoDataResponseModel> listAbusedItems(
            @Field("phone") String phone,
            @Field("fromUserId") int fromUserId
    );

    @FormUrlEncoded
    @POST("public/api/delete_abused_item")
    Call<NoDataResponseModel> deleteAbusedItem(
            @Field("user_id") int userId,
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("public/api/get_post_by_user")
    Call<UserAllPostResponseModel> getPostbyUser(
            @Field("user_id") String userId
    );

    @FormUrlEncoded
    @POST("public/api/deactivate_item")
    Call<NoDataResponseModel> deactivateItem(
            @Field("user_id") int userId,
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("public/api/activate_item")
    Call<NoDataResponseModel> activateItem(
            @Field("user_id") int userId,
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("public/api/delete_item")
    Call<NoDataResponseModel> deleteItem(
            @Field("user_id") String userId,
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("public/api/delete_item_images")
    Call<NoDataResponseModel> deleteItemImages(
            @Field("id") int id
    );

    //@FormUrlEncoded
    //@POST("/public/api/add_post")
    //Call<AddPostResponseModel> addPost(
    //@Body RequestBody ("user_id") int userId,
    //@Field("fromUserId") int fromUserId,
    //@Field("priority") int priority,
    //@Field("imgUrl") File imgUrl,
    //@Field("showComments") int showComments,
    //@Field("category") int category,
    //@Field("subCategory") int subCategory,
    //@Field("itemTitle") String itemTitle,
    //@Field("itemDesc") String itemDesc,
    //@Field("showPhoneNumber") int showPhoneNumber,
    //@Field("showMessage") int showMessage,
    //@Field("city") String city,
    //@Field("country") String country,
    //@Field("images[]") File images,
    //@Part MultipartBody.Part MediaFile
    //);

    @POST("public/api/update_post")
    Call<NoDataResponseModel> updatePost(
            @Body RequestBody file
    );

    @FormUrlEncoded
    @POST("public/api/get_single_category")
    Call<NoDataResponseModel> getSingleCategory(
            @Field("category_id") int category_id
    );

    @FormUrlEncoded
    @POST("public/api/category")
    Call<NoDataResponseModel> category(
            @Field("category_id") int category_id
    );

//Dashboard API call here

    @FormUrlEncoded
    @POST("public/api/item_search")
    Call<SearchResponseModel> itemSearch(
            @Field("user_id") String user_id,
            @Field("search") String search
    );

    @FormUrlEncoded
    @POST("public/api/get_all_item_by_category")
    Call<AllPostResponseModel> getAllItembyCategory(
            @Field("category_id") int category_id,
            @Field("city") String city,
            @Field("subCategory") int subCategory,
            @Field("removeAt") int removeAt
    );

    @FormUrlEncoded
    @POST("public/api/get_item")
    Call<GetPostResponseModel> getItem(
            @Field("user_id") int user_id,
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("public/api/get_item")
    Call<ResponseBody> getItem3(
            @Field("user_id") int user_id,
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("public/api/get_item")
    Call<GetPostResponseModel> getItem2(
            @Field("id") int id

    );

    //Item Search API call here

    @FormUrlEncoded
    @POST("public/api/get_user_profile")
    Call<GetUserProfileResponseModel> getUserProfile(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("public/api/update_user_profile")
    Call<UpdateProfileResponseModel> updateUserProfile(
            @Field("user_id") String user_id,
            @Field("country") String country,
            @Field("city") String city,
            @Field("fullname") String fullname
    );

    @FormUrlEncoded
    @POST("public/api/update_user_device_token")
    Call<UpdateDeviceInfoResponse> updateUserDeviceToken(
            @Field("user_id") String user_id,
            @Field("device_token") String device_token,
            @Field("device_type") String device_type
    );

    //check this one
    @FormUrlEncoded
    @POST("public/api/get_chat_list_by_user")
    Call<NoDataResponseModel> getChatListbyUser(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("public/api/get_single_chat_by_user")
    Call<NoDataResponseModel> getSingleChatbyUser(
            @Field("user_id") int user_id,
            @Field("chat_id") int chat_id
    );

    @FormUrlEncoded
    @POST("public/api/send_message")
    Call<NoDataResponseModel> sendMessage(
            @Field("user_id") int user_id,
            @Field("message") String message,
            @Field("to_user_id") int to_user_id,
            @Field("chat_id") int chat_id
    );

    @FormUrlEncoded
    @POST("public/api/send_new_message")
    Call<NoDataResponseModel> sendNewMessage(
            @Field("user_id") int user_id,
            @Field("message") String message,
            @Field("to_user_id") int to_user_id,
            @Field("chat_id") int chat_id
    );

    @FormUrlEncoded
    @POST("public/api/delete_message")
    Call<NoDataResponseModel> sendNewMessage(
            @Field("user_id") int user_id,
            @Field("chat_id") int chat_id,
            @Field("message_id") int message_id
    );

    @FormUrlEncoded
    @POST("public/api/get_by_item_report")
    Call<NoDataResponseModel> getbyItemReport(
            @Field("itemId") int itemId
    );

    @FormUrlEncoded
    @POST("public/api/get_all_notify")
    Call<NotificationModel> getAllNotify(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("public/api/add_delivery")
    Call<AddDeliveryResponseModel> addDelivery(
            @Field("user_id") String fromUserId,
            @Field("itemTitle") String itemTitle,
            @Field("itemDesc") String itemDesc,
            @Field("category") String category,
            @Field("subCategory") String subcategory,
            @Field("showPhoneNumber") String showPhoneNumber,
            @Field("showComments") String showComments,
            @Field("showMessage") String showMessage,
            @Field("city") String city,
            @Field("country") String country,
            @Field("phone") String imgUrl
    );

    @FormUrlEncoded
    @POST("public/api/add_msgs")
    Call<NoDataResponseModel> addMsgs(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("public/api/read_msg")
    Call<NoDataResponseModel> readMsgs(
            @Field("user_id") int user_id,
            @Field("read_msg") int read_msg
    );

    @FormUrlEncoded
    @POST("public/api/sliders")
    Call<RelatedAdHomeModel> sliders(
            @Field("pass") String pass
    );


    @FormUrlEncoded
    @POST("public/api/like_item")
    Call<AddCommentResponseModel> likeItem(
            @Field("pass") String pass,
            @Field("user_d") int userId,
            @Field("id") int itemId,
            @Field("status") String status

    );


    @FormUrlEncoded
    @POST("public/api/comments")
    Call<ViewCommentsResponseModel> viewComments(
            @Field("pass") String pass,
            @Field("itemId") String itemId
    );


    @FormUrlEncoded
    @POST("public/api/home")
    Call<GetPostResponseModel> getPost(
            @Field("pass") String pass,
            @Field("id") String itemId
    );


    @FormUrlEncoded
    @POST("public/api/home")
    Call<GetPostResponseModel> getPostWithLogin(
            @Field("pass") String pass,
            @Field("id") String itemId,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("public/api/deliveries")
    Call<UserAllPostResponseModel> getAllDelivery(
            @Field("user_id") int userId
    );

    @FormUrlEncoded
    @POST("public/api/delete_deliver")
    Call<UserAllPostResponseModel> deleteDelivery(
            @Field("user_id") String userId,
            @Field("id") String id

    );

//     @FormUrlEncoded
//     @POST("/api/public/item")
//     Call<UserAllPostResponseModel> addDelivery(
//             @Field("pass") String pass,
//             @Field("username") String username,
//             @Field("location") String location,
//             @Field("title") String itemTitle,
//             @Field("description") String description,
//             @Field("category") String category,
//             @Field("sub_category") String subcategory,
//             @Field("showComments") String showComments,
//             @Field("showPhone") String showPhone,
//             @Field("showMessage") String showMessage,
//             @Field("country") String country,
//             @Field("city") String city

//     );
/*
    @FormUrlEncoded
    @POST("/api/articles")
    Call<AllArticlesResponseModel> getAllArticles(
            @Field("pass") String pass
    );*/

    @FormUrlEncoded
    @POST("public/api/articles")
    Call<AllArticlesResponseModel> getAllArticles(
            @Field("pass") String pass
    );


    @FormUrlEncoded
    @POST("public/api/favorites")
    Call<FavResponseModel> doFavAd(
            @Field("pass") String pass,
            @Field("userId") String userId,
            @Field("itemId") String itemId,
            @Field("status") String status
    );

//     @FormUrlEncoded
//     @POST("/api/public/favorites")
//     Call<GetFavouritesResponse> getMyFav(
//             @Field("pass") String pass,
//             @Field("userId") String userId
//     );

//     @FormUrlEncoded
//     @POST("/api/public/item")
//     Call<NoDataResponseModel> deleteItem(
//             @Field("pass") String pass,
//             @Field("username") String username,
//             @Field("id") String id
//     );

    //     @FormUrlEncoded
//     @POST("/api/public/item")
//     Call<NoDataResponseModel> deleteItemImage(
//             @Field("pass") String pass,
//             @Field("id") String id
//     );
    @FormUrlEncoded
    @POST("public/api/delete_item_images")
    Call<NoDataResponseModel> deleteItemImage(
            @Field("id") String id
    );

    @GET("public/api/cities")
    Call<CityNameResponseModel> getAllCityNames(
    );

}
