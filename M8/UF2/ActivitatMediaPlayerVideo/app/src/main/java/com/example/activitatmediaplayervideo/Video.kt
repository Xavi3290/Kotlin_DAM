package com.example.activitatmediaplayervideo

var listVideos:ArrayList<Video> = arrayListOf()

class Video (
    val nom:String,
    val url:String
)

fun omplirVideo(){
    listVideos.addAll(
        listOf(
            Video("Elephant Dream","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
            Video("Big Buck Bunny","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
            Video("For Bigger Blazes","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"),
        )
    )
}