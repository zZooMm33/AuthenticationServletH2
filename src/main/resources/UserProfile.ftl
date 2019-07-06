<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Hi ${name} !</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Lato|Montaga" rel="stylesheet">

    <style>
        .center{
            text-align: center;
        }
        h3{
            margin-bottom: 10px;
        }
        p{
            margin-bottom: 0px;
            margin-top: 5px;
        }
    </style>
</head>
<body>



<div class="container">
    <div class="row mt-3">
        <div class="col-4"></div>
        <div class="col-4">
            <div class="center">
                <h3>Hi ${name} !</h3>
                <p>Your mail - ${mail}</p>
                <p>Your Info - ${info}</p>
                <button type="button" id = "logout" class="btn btn-danger mt-3">Logout</button>
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
        $("#logout").bind("click", function () {
            $.ajax({
                type: "POST",
                url: "${webAddress}logout",
                success: function(data) {
                    window.location.replace("${webAddress}authentication");
                }
            });
        });
    });
</script>

</body>
</html>