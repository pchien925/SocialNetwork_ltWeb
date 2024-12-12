<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>UTE SOCIAL</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <style>
    .navbar {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      z-index: 1030; /* Đặt z-index cao để header nổi trên các phần tử khác */
      background-color: #ffffff;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      padding: 0.5rem 1rem;
    }
    body {
      padding-top: 70px; /* Thêm khoảng cách để tránh nội dung bị che bởi header */
    }

    .navbar-brand {
      font-size: 1.8rem;
      font-weight: bold;
      color: #4267B2;
    }
    .navbar-brand:hover {
      color: #2e5aac;
    }
    .notification-badge {
      position: absolute;
      top: 0;
      right: 0;
      transform: translate(50%, -50%);
      background-color: red;
      color: white;
      border-radius: 50%;
      font-size: 0.8rem;
      width: 18px;
      height: 18px;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .profile-img {
      width: 40px;
      height: 40px;
      border-radius: 50%;
      object-fit: cover;
    }
    @media (max-width: 576px) {
      .navbar-nav {
        flex-direction: row;
        justify-content: flex-end;
        gap: 15px;
        margin-left: auto;
      }
      .profile-img {
        width: 35px;
        height: 35px;
      }
    }
  </style>
</head>
<body>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <!-- Logo -->
      <a class="navbar-brand" href="/posts/newsfeed">
         UTE SOCIAL
      </a>

      <!-- Toggler Button for Small Screens -->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Collapsible Content -->
      <div class="collapse navbar-collapse" id="navbarContent">
        <!-- Search Bar -->
         <form action="/user/searchpaginated" class="d-flex justify-content-center w-100 my-2">
          <input type="text" name="name" id="name" class="form-control" placeholder="Search Friends By Name">
                    <button type="submit" class="btn">
                      <i class="fas fa-search"></i>
                    </button>
        </form>

        <!-- Navigation Icons -->
        <ul class="navbar-nav d-flex align-items-center ms-auto gap-3">
          <li class="nav-item position-relative">
            <a href="/posts/newsfeed" title="Home" class="position-relative">
              <button class="btn btn-light position-relative">
                <i class="fas fa-home"></i>
              </button>
            </a>
          </li>
          <li class="nav-item position-relative">
            <a href="/user/user-list" title="Friends" class="position-relative">
              <button class="btn btn-light position-relative">
                <i class="fas fa-user-friends"></i>
              </button>
            </a>
          </li>
<li class="nav-item position-relative">
  <a href="/api/messages/" class="btn btn-light position-relative" title="Messenger">
    <i class="fas fa-comment-dots"></i>
  </a>
</li>

          <li class="nav-item position-relative">
            <button class="btn btn-light position-relative" title="Notifications">
              <i class="fas fa-bell"></i>

            </button>
          </li>
          <li id="navItem" class="nav-item">

          </li>

        </ul>
      </div>
    </div>
  </nav>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

   <script type="text/javascript">
          $(document).ready(function() {
              $.ajax({
                  url: "http://localhost:8888/api/user/profile", // Full URL to your API
                  method: "GET",
                  dataType: "json", // Ensure the response is expected as JSON
                  success: function(response) {
                      console.log("API Response: ", response);

                      // Assuming response is an object with a property that holds the number of total users
                      // Example: response might be { totalUsers: 1250 }

                     console.log("Data: ", response);
                     let user = response;

                      // Generate HTML to display the total users
                      let AVT = `
                          <a href="/profile/` +user.id+ `" title="Profile">
                                            <!-- Profile Image, ID added to update dynamically -->
                                            <img id="profileImg" src="` +user.avatar+ `" alt="Profile" class="profile-img">
                                        </a>
                      `;




                      // Insert the generated HTML into the #card-users element
                      $("#navItem").html(AVT);

                  },
                  error: function(xhr, status, error) {
                      console.error("Error fetching data: ", error);
                      // Handle error here (e.g., display a message to the user)
                  }
              });
          });
      </script>
</body>
</html>
