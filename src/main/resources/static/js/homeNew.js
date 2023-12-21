let userId= window.sessionStorage.getItem("userID");
let userAvatar=window.sessionStorage.getItem("userAvatar");
window.onload = function() {
    console.log('home part!')

    console.log(userAvatar);
    document.querySelector('.profileavatar').src = userAvatar;

    $('#leftpartavatar').attr('src', userAvatar);
    $('#editinfo').css("display","none");
    $('#groupinfo').css("display","none");
    $('#deletefriendbutton').css("display","none");
    getUserInfo();
    getFriendList();

}

$('#leftpartavatar').click(function () {
    getUserInfo();
})

// 当点击保存按钮时
$('.savebutton').click ( function () {
    // 显示展示信息区域
    $('#showinfomation').css("display","block");
    // 隐藏编辑信息区域
    $('#editinfo').css("display","none")
    updateUserInfo();
})

$('.deletebutton').click ( function () {
    // 显示展示信息区域
    $('#showinfomation').css("display","none");
    // 隐藏编辑信息区域
    $('#editinfo').css("display","block")
})

function getUserInfo(){

    $.ajax({
        url: 'http://localhost:8080/user/getUserInfo',
        type: 'GET',
        data: {
            userId: userId
        },
        success: function(response) {
            // 处理后端返回的响应
            console.log(response);
            $('#editinfobutton').css("display","block")
            $('#deletefriendbutton').css("display","none")
            $('#groupinfo').css("display","none");
            $('#showinfomation').css("display","block");
            $('#editinfo').css("display","none")
            $('#info_id').text(userId);
            $('#profileheadername').text(response.data.name);
            $('#info_name').text(response.data.name);
            $('#info_address').text(response.data.address);
            $('#info_email').text(response.data.email);
            $('#info_signature').text(response.data.signature);
            //$('#avatar3').val(response.data.avatar);
            $('#profileAvatar').attr('src', response.data.avatar);
            $('#info_phone').text(response.data.phone);
        },
        error: function(error) {
            console.error(error);
            alert("获取信息失败");
        }
    });
    $('#deletefriendbutton').css("display","none");
    $('#editinfobutton').css("display","block");
}

$('#leftpartavatar').click(function () {
    getUserInfo();
})

$('#deletefriendbutton').click(function () {
    let friendId=$('#info_id').text();
    deleteFriend(friendId);
})

function deleteFriend(friendId){
    $.ajax({
        url:"http://localhost:8080/friend/deleteFriend",
        type:"POST",
        data:{
            userID:userId,
            friendID:friendId
        },
        dataType:'json',
        async:false,
        success:function(res){
            alert("删除好友成功！")
            location.reload();
        },
        error:function(){
            alert("删除好友失败！")
        }
    })
}

function updateUserInfo(){
        let newName=$('#name3').val();
        let newAddress=$('#address3').val();
        let newEmail=$('#email3').val();
        let newSignature=$('#signature3').val();
        let newAvatar=$('#avatar3').val();
        let newPhone=$('#phone3').val();


        if(newAddress===""||newPhone===""||newSignature===""||newEmail===""||newName===""||newAvatar===""){
            alert("请填写完整信息");
            return;
        }

        console.log(userId,newName,newAddress,newAvatar,newEmail,newPhone,newSignature)
        $.ajax({
            url: 'http://localhost:8080/user/updateUserInfo',
            type: 'POST',
            data: {
                userID: userId,
                newName: newName,
                newAddress: newAddress,
                newAvatar: newAvatar,
                newEmail: newEmail,
                newPhone: newPhone,
                newSignature: newSignature
            },
            success: function(response) {
                // 处理后端返回的响应
                alert("修改成功！")
                getUserInfo()
            },
            error: function(error) {
                console.error(error);
                alert("修改失败");
            }
        });
    }



//左边部分
$('#contactfriend').click(function () {
    getFriendList();
})

$('#contactgroup').click(function () {

    getGroupList();
})

$('#menuitem1').click(function () {
    $('.contactlist').css("display","block");
    $('.addcontactlist').css("display","none");
    $('#menuitem1').attr('src', '/img/friends2.png');
    $('#menuitem2').attr('src', '/img/addcontact2.png');
})

$('#menuitem2').click(function () {
    $('.contactlist').css("display","none");
    $('.addcontactlist').css("display","block");
    $('#menuitem1').attr('src', '/img/friends1.png');
    $('#menuitem2').attr('src', '/img/addcontact1.png');
})



function getFriendList(){

    $('#contactfriend').css("color","rgb(129, 67, 186)");
    //$('#contactfriend').css("font-weight","600");
    $('#contactgroup').css("color","rgb(134, 134, 134)");
    //$('#contactgroup').css("font-weight","400");
    $('.groupitem').css("display","none");
    $('.frienditem').css("display","flex");
    $('#friendsinfo').css("display","block");

    let friendList=[];
    $.ajax({
        url:"http://localhost:8080/friend/getFriend",
        type:"GET",
        data:{
            userID:userId
        },
        dataType:'json',
        async:false,
        success:function(res){
            friendList=res.data;
            console.log(friendList);
            // 清空现有的好友列表
            $('#friendsContainer').empty();



            // 遍历好友列表并添加到页面
            friendList.forEach(function(friend) {
                let friendItemHtml =
                    '<div class="frienditem" data-userid="' + friend.userId + '">' + // 存储用户ID
                    '<div class="friendavatar">' +
                    '<img src="' + friend.avatar + '" class="avatar">' +
                    '</div>' +
                    '<div class="friendtext">' +
                     '<div class="tipname">' + friend.name + '</div>' +
                    //'<a href="#" class="tipname" onclick="talk(this)" style="text-decoration: none;">' + friend.name + '</a>' +
                    '</div>' +
                    '</div>';
                // let $friendItem = $(friendItemHtml).on('dblclick', function() {
                //     console.log("Friend item double-clicked. User ID:", $(this).data('userid'));
                //     getFriendInfo($(this).data('userid'));
                // });

                $('#friendsContainer').on('click', '.frienditem', function() {
                    console.log("单击事件触发");
                    getFriendInfo($(this).data('userid'));
                });


                $('#friendsContainer').append(friendItemHtml);
            });
        },
        error:function(){
            alert("获取好友信息失败！")
        }
    })
}

function getFriendInfo(userId) {
    console.log(userId);
    $.ajax({
        url: 'http://localhost:8080/user/getUserInfo',
        type: 'GET',
        data: {
            userId: userId // 使用传入的用户ID
        },
        success: function(response) {
            // 更新页面上的用户信息
            $('#deletefriendbutton').css("display","block");
            $('#editinfobutton').css("display","none");
            $('#groupinfo').css("display","none");
            $('#editinfo').css("display","none");
            $('#showinfomation').css("display","block");
            $('#profileheadername').text(response.data.name);
            $('#info_id').text(response.data.userId);
            $('#info_name').text(response.data.name);
            $('#info_address').text(response.data.address);
            $('#info_email').text(response.data.email);
            $('#info_signature').text(response.data.signature);
            $('#profileAvatar').attr('src', response.data.avatar);
            $('#info_phone').text(response.data.phone);
        },
        error: function() {
            alert("获取信息失败");
        }
    });
}


function  getGroupList() {
    $('#contactgroup').css("color","rgb(129, 67, 186)");
    //$('#contactgroup').css("font-weight","600");
    $('#contactfriend').css("color","rgb(134, 134, 134)");
    //$('#contactfriend').css("font-weight","400");
    $('.groupitem').css("display","flex");
    $('.frienditem').css("display","none");
    $('#friendsinfo').css("display","none");

    let groupList=[];
    $.ajax({
        url:"http://localhost:8080/group/getUserGroup",
        type:"GET",
        data:{
            userID:userId
        },
        dataType:'json',
        async:false,
        success:function(res){
            groupList=res.data;
            console.log(groupList);
            $('#groupsContainer').empty();

            groupList.forEach(function(group) {
                let groupItemHtml =
                    '<div class="groupitem" data-groupid="' + group.groupId + '">' +
                    '<div class="groupavatar">' +
                    '<img src="' + group.avatar + '" class="avatar">' +
                    '</div>' +
                    '<div class="grouptext">' +
                    //'<div class="groupname">' + group.name + '</div>' +
                    '<a href="#" class="groupname" onclick="talk(this,2)" style="text-decoration: none;">' + group.name + '</a>'
                    '</div>' +
                    '</div>';

                $('#groupsContainer').on('click', '.groupitem', function() {
                    console.log("单击事件触发");
                    getGroupInfo($(this).data('groupid'));
                });

                $('#groupsContainer').append(groupItemHtml);
            });
        },
        error:function(){
            alert("获取群聊信息失败！")
        }
    })
}

function getGroupInfo(groupId) {
    console.log(groupId);
    $.ajax({
        url: 'http://localhost:8080/group/getGroupInfo',
        type: 'GET',
        data: {
            groupID: groupId // 使用传入的ID
        },
        success: function(response) {
            console.log(response);
            // 更新页面上的用户信息
            $('#groupinfo').css("display","block");
            $('#showinfomation').css("display","none");
            $('#editinfo').css("display","none")
            //$('#editinfobutton').css("display","none");
            $('#profileheadername').text(response.data.name);
            $('#info_name_group').text(response.data.name);
            $('#info_id_group').text(response.data.groupId);
            $('#profileAvatar').attr('src', response.data.avatar);
            $('#info_time_group').text(response.data.createTime);
            $('#info_master_group').text(response.data.groupMaster);
        },
        error: function() {
            alert("获取信息失败");
        }
    });
}

$('#addfriendbutton').click(function (){
    let friendId=$('#addfriendid').val();
    addFriend(friendId)
})

$('#addgroupbutton').click(function (){
    let groupId=$('#addgroupid').val();
    addGroup(groupId)
})

$('#creategroupbutton').click(function (){
        let groupName=$('#creategroupname').val();
        createGroup(groupName)
    }
)

function addFriend(friendName){
    //根据用户名获取用户id
    let friendId=0;
    if(friendName===""){
        alert("请输入用户名！")
        return;
    }
    $.ajax({
        url:"http://localhost:8080/user/getUserByName",
        type:"GET",
        data:{
            name:friendName
        },
        dataType:'json',
        async:false,
        success:function(res){
            if(res.code===-1){
                alert("请确认用户存在！")
                return;
            }
            friendId=res.data.userId;
        },
        error:function(){
            alert("请确认用户存在！")
        }
    })
    if(friendId===0){
        return;
    }
    $.ajax({
        url:"http://localhost:8080/friend/addFriend",
        type:"POST",
        data:{
            userID:userId,
            friendID:friendId
        },
        dataType:'json',
        async:false,
        success:function(res){
            alert("添加好友成功！")
            location.reload();
        },
        error:function(){
            alert("添加好友失败！")
        }
    })
}

function addGroup(groupName){
    //根据群聊名获取群聊id
    let groupId=0;
    if(groupName===""){
        alert("请输入群聊名！")
        return;
    }
    console.log(groupName)
    $.ajax({
        url:"http://localhost:8080/group/getGroupByName",
        type:"GET",
        data:{
            name:groupName
        },
        dataType:'json',
        async:false,
        success:function(res){
            console.log(res);
            if(res.code!==-1) {
                groupId = res.data;
            }
            else{
                alert("请确认群聊存在！")
            }
        },
        error:function(){
            alert("请确认群聊存在！")

        }
    })
    if(groupId===0){
        return;
    }
    $.ajax({
        url:"http://localhost:8080/group/joinGroup",
        type:"POST",
        data:{
            userID:userId,
            groupID:groupId
        },
        dataType:'json',
        async:false,
        success:function(res){
            console.log(res);
            alert("加入群聊成功！")
            location.reload();
        },
        error:function(){
            alert("加入群聊失败！")
        }
    })
}

function createGroup(groupName){
    if(groupName===""){
        alert("请输入群聊名！")
        return;
    }
    let groupId=0;
    $.ajax({
        url:"http://localhost:8080/group/getGroupByName",
        type:"GET",
        data:{
            name:groupName
        },
        dataType:'json',
        async:false,
        success:function(res){
            console.log(res);
            if(res.code===0) {//群聊存在
                groupId = res.data;
                alert("群聊已存在！")
            }
        },
        error:function(){
            alert("创建失败")
        }
    })
    if(groupId!==0){
        return;
    }
   // alert("让我看看!")
    $.ajax({
        url:"http://localhost:8080/group/createGroup",
        type:"POST",
        data:{
            userID:userId,
            groupName:groupName
        },
        dataType:'json',
        async:false,
        success:function(res){
            alert("创建群聊成功！")
            location.reload();
        },
        error:function(){
            alert("创建群聊失败！")
        }
    })
}

