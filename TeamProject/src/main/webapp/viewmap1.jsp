<%-- 
    Document   : mymap
    Created on : 25 Απρ 2020, 4:22:53 μμ
    Author     : ioann
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <c:set var="username" value="${pageContext.request.userPrincipal.name}"/>
   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <title>My Google Map</title>

    <style>
        body{
            font-family: Arial, Helvetica, sans-serif;
            
        }


        #map {
            height: 500px;
            width: 850px;
            margin-top: 10px;
            margin-bottom: 40px;
            
        }

        #headerdiv{
            padding: 10px;
            margin-left: 60px;
            margin-top: 20px;
        }

/*        form select{
            width: 85%;
            height: 50px;
            border: solid lightgray 0.5px;
            border-radius: 5px;
            margin-bottom: 20px;
            margin-left: 40px;
        }*/
    
        
        #mapinput{
            width: 80%;
            height: 40px;
            padding: 5px;
            margin-top: 5px;
            margin-bottom: 5px;
            border: solid 0.5px lightblue;
        }
        
        #mapinput:focus{
            border-color: blue;
        }

    </style>
</head>

<body>

      <div class="row row-no-gutters">
        <div class="col-10"></div>
        <div class="col-2">
             
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>

                    <h5>${pageContext.request.userPrincipal.name}</h5> <a onclick="document.forms['logoutForm'].submit()">Logout</a>
                </c:if>
          
        </div>
    </div>
   
    <header>
    <div class="row row-no-gutters">
     <div class="col-10" id="headerdiv">
        <h1>Map</h1>
     </div>
    </div>


</header>
    <hr>

    <div class="row row-no-gutters">
        <div class="col-3"></div>
        <div class="col-6">
            
            <div>
                <input type="text" id="stname" name="stname" value="${st.studentFirstName}" readonly> <span></span>
                <input type="text" id="stlname" name="stlname" value="${st.studentLastName}" readonly> <br>
                
                <input type="text" id="area" name="area" value="${st.addressArea}" readonly><span></span>
                <input type="text" id="street" name="street" value="${st.streetName}" readonly> <br>
                <input type="text" id="number" name="number" value="${st.addressNumber}" readonly> <span></span>
                  <input type="text" id="tk" name="addressTK" value="${st.addressTK}" readonly>
                    
                  <p id="mapinput"> ${st.addressArea}, ${st.streetName}, ${st.addressNumber}, ${st.addressTK} <p> 
            </div>
         
<!--            
            <div class="row row-no-gutters">
                <div class="col-3"></div>
                <div class="col-6"><input type="text" placeholder="Search..." id="mapinput"></div>
                <div class="col-3"></div>
               </div>-->








        <div class="col-3"></div>
    </div>
 </div>


    <div class="row row-no-gutters">

        <div class="col-md-2"></div>

        <div class="col-md-8">
             <div id="map"></div>
            </div>

    <div class="col-md-2"></div>
</div>


    <script>

        function initMap() {
            
            const input =document.getElementById("mapinput");
 
            var stlocation = input.innerText;
            console.log(stlocation);

            var address = stlocation;

            // Call Geocode       
            var cords = geocode(address);
            console.log(cords);
            function geocode(location) {
                axios.get('https://maps.googleapis.com/maps/api/geocode/json', {
                    params: {
                        address: location,
                        key: ''
                    }
                })
                    .then(function (response) {
                        // Log full response
                        console.log(response);
                        // // Formated Address
                        console.log(response.data.results[0].formatted_address);

                        // // Geometry
                        var latitude = response.data.results[0].geometry.location.lat;
                        var longitude = response.data.results[0].geometry.location.lng;

                        // Map options 
                        var options = {
                            zoom: 15,
                            center: { lat: latitude, lng: longitude }
                        };

                        // New map
                        var map = new google.maps.Map(document.getElementById('map'), options);

                        addMarker({ lat: latitude, lng: longitude });
                        // Add Marker
                        function addMarker(coords) {
                            var marker = new google.maps.Marker({
                                position: coords,
                                map: map
                            });
                        }

                        var cords = { latitude: lat, longitude: lng }
                        console.log(cords);
                        return cords;

                    })
                    .catch(function (error) {
                        // console.log(error);
                    });
            }

        }
    </script>

    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDw3ZCrqAJNYJy1dokeKp2FTwTqdM_XIDs&callback=initMap">
        </script>
</body>

</html>

