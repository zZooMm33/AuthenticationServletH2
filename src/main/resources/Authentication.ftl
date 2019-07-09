<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>controllers.Authentication</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Lato|Montaga" rel="stylesheet">

    <style>
        .center{
            text-align: center;
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
            <div class="center">

                <div class="alert alert-danger hide mt-3" id="info" role="alert"></div>

                <form class="mt-3">
                    <div class="form-group">
                        <label for="exampleInputUserName">User name</label>
                        <input type="text" class="form-control" id="exampleInputUserName" placeholder="Enter user name">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword">Password</label>
                        <input type="password" class="form-control" id="exampleInputPassword" placeholder="Password">
                    </div>

                    <button type="button" id="login" class="btn btn-success">Login</button>
                    <button type="button" id="reg" class="btn btn-outline-secondary">Registration</button>
                </form>
            </div>
        </div>
        <div class="col-4"></div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<script>
    $(document).ready(function () {
        $("#reg").bind("click", function () {
            window.location.replace("${webAddress}reg");
        });
        $("#login").bind("click", function () {
            $("#info").hide();
            var login = $("#exampleInputUserName").val(), pass = $("#exampleInputPassword").val();
            if (login.length == 0){
                $("#info").text("Enter user name !");
                $("#info").show();
            }
            else if (pass.length == 0){
                $("#info").text("Enter password !");
                $("#info").show();
            }
            else {
                $.ajax({
                    type: "POST",
                    url: "${webAddress}login",
                    data: "&login="+ login + "&pass="+ pass,
                    success: function(data) {
                        if (data.length != 0){
                            if (data.indexOf("loginFail") != -1){
                                $("#info").text("This login does not exist!");
                                $("#info").show();
                            }
                            else if (data.indexOf("passFail") != -1){
                                $("#info").text("Error in password!");
                                $("#info").show();
                            }
                        }
                        else {
                            window.location.replace("${webAddress}user");
                        }
                    }
                });
            }
        });
    });
</script>

</body>
</html>