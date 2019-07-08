<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>controllersPostRequest.Registration</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Lato|Montaga" rel="stylesheet">

    <style>
        .center{
            text-align: center;
        }
        .left{
            text-align: left;
        }
        .hide{
            display: none;
        }
    </style>
</head>
<body>



<div class="container">
    <div class="row">
        <div class="col-4"></div>
        <div class="col-4">
            <h2 class="mt-3 center reg_hide">Registration</h2>
            <div class="alert alert-danger hide mt-3" id="infoError" role="alert"></div>
            <div class="alert alert-success hide mt-3" id="infoSuccess" role="alert"></div>

            <form class="mt-3 reg_hide">
                <div class="left">
                    <div class="form-group">
                        <label for="exampleInputUserName" class="left">User name</label>
                        <input type="text" class="form-control" id="exampleInputUserName" placeholder="Enter user name">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputEmail" class="left">Email address</label>
                        <input type="email" class="form-control" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Enter email">
                    </div>

                    <div class="form-group">
                        <label for="exampleFormControlTextareaInfo" class="left">Info</label>
                        <textarea class="form-control" id="exampleFormControlTextareaInfo" rows="3"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputPassword" class="left">Password</label>
                        <input type="password" class="form-control" id="exampleInputPassword" placeholder="Password">
                    </div>
                </div>
            </form>

            <div class="center"><button type="button" id="reg" class="btn btn-primary reg_hide">Registration</button></div>
            <div class="center"><button type="button" id="login" class="btn btn-success hide">Go to authentication</button></div>
        </div>
        <div class="col-4"></div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<script>
    $(document).ready(function () {

        $("#login").bind("click", function () {
            window.location.replace("${webAddress}authentication");
        });

        $("#reg").bind("click", function () {

            $("#infoError").hide();
            $("#infoSuccess").hide();

            var login = $("#exampleInputUserName").val(),
                pass = $("#exampleInputPassword").val(),
                info = $("#exampleFormControlTextareaInfo").val(),
                mail = $("#exampleInputEmail").val();


            if (login.length == 0){
                $("#infoError").text("Enter user name !");
                $("#infoError").show();
            }
            else if (!validateEmail(mail)){
                $("#infoError").text("Incorrect E-Mail Address !");
                $("#infoError").show();
            }
            else if (info.length == 0){
                $("#infoError").text("Enter info !");
                $("#infoError").show();
            }
            else if (pass.length == 0){
                $("#infoError").text("Enter password !");
                $("#infoError").show();
            }
            else{
                $.ajax({
                    type: "POST",
                    url: "${webAddress}reg",
                    data: "&login="+ login + "&pass="+ pass + "&info="+ info + "&mail="+ mail,
                    success: function(data) {
                        if (data.length != 0){
                            // логин уже занят
                            if (data.indexOf("login") != -1){
                                $("#infoError").text("This login is already in use!");
                                $("#infoError").show();
                            }
                            // email уже занят
                            else if (data.indexOf("mail") != -1){
                                $("#infoError").text("This email is already in use!");
                                $("#infoError").show();
                            }
                        }
                        else {
                            // очищаем поля и выводим сообщение об успешной регистрации
                            $("#exampleInputUserName").val("");
                            $("#exampleInputPassword").val("");
                            $("#exampleFormControlTextareaInfo").val("");
                            $("#exampleInputEmail").val("");

                            $(".reg_hide").hide();
                            $("#login").show();

                            $("#infoSuccess").text("Registration completed successfully !");
                            $("#infoSuccess").show();
                        }

                    }
                });
            }
        });
    });


    function validateEmail(email) {
        var pattern  = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return pattern.test(email);
    }

</script>

</body>
</html>