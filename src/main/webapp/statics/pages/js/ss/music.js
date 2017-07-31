function getPlayList() {
    $.ajax({
        url : "/ss/music/getUploadList.do",
        type : "POST",
        dataType : "json",
        success: function(data) {
            if (data.code == 0) {
                // window.href = "index.html";
                var data = data.data;
                var playlist = [];
                for (var i in data) {
                    playlist.push({trackName : data[i].name, file : data[i].fileUrl, thumb : "img/player/player-back.jpg",trackArtist: data[i].singer, trackAlbum : data[i].album});
                }
                var t = {playlist : playlist,autoPlay:false};
                $(".jAudio--player").jAudio(t);
            } else {
                alert(data.msg);
            }
        },
        error : function() {
            alert("系统异常");
        }
    })
}
getPlayList();
// $("#_play").click(function() {
    /**
     * var t = {
	playlist:[
		{
		  file: "夜曲.mp4",
		  thumb: "img/player/player-back.jpeg",
		  trackName: "夜曲",
		  trackArtist: "周杰伦",
		  trackAlbum: "十一月肖邦",
		}
     */
    
// })