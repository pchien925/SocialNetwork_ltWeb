<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f7fc;
    }

    .container {
        display: flex;
        justify-content: space-between;
        margin: 20px;
    }

    .card {
        background-color: #ffffff;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        padding: 20px;
        text-align: center;
        width: 30%;
    }

    .card h3 {
        font-size: 2em;
        margin-bottom: 10px;
        color: #333;
    }

    .card p {
        font-size: 1.2em;
        color: #777;
    }

    .card .total {
        font-size: 3em;
        font-weight: bold;
        color: #4CAF50;
    }

    .card .icon {
        font-size: 2em;
        margin-bottom: 15px;
    }

    .card-users .icon {
        color: #1E88E5;
    }

    .card-posts .icon {
        color: #FF5722;
    }

    .card-reports .icon {
        color: #FF9800;
    }
</style>

 <div class="container">
        <div class="card card-users" id="card-users">

        </div>

        <div class="card card-posts" id="card-posts">
            <div class="icon">📝</div>
            <h3>Total Posts</h3>
            <p class="total" id="total-posts">5,432</p>
        </div>

        <div class="card card-reports" id="card-reports">
            <div class="icon">📊</div>
            <h3>Total Reports</h3>
            <p class="total" id="total-reports">120</p>
        </div>

    </div>


    <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                url: "http://localhost:8888/api/mod/dash/count-users", // Full URL to your API
                method: "GET",
                dataType: "json", // Ensure the response is expected as JSON
                success: function(response) {
                    console.log("API Response: ", response);

                    // Assuming response is an object with a property that holds the number of total users
                    // Example: response might be { totalUsers: 1250 }

                    let totalUsers = response; // Access totalUsers from the response object (update based on actual response structure)
                    console.log("Total Users Data: ", totalUsers);

                    // Generate HTML to display the total users
                    let totalUser = `
                        <div class="icon">👥</div>
                        <h3>Total Users</h3>
                        <p class="total" id="total-users">` +totalUsers.totalUsers+ `</p>
                    `;

                    // Insert the generated HTML into the #card-users element
                    $("#card-users").html(totalUser);
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching data: ", error);
                    // Handle error here (e.g., display a message to the user)
                }
            });
        });
    </script>

