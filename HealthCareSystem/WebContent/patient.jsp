
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-dark py-0">
        <a class="navbar-brand" href="#">
          <span class="h5 text-light">Health Care</span>
        </a>
      </nav>
    <div class="container">
               
        <div class="row  mt-4" >
            <div class="col-12" >
                <div class="card  shadow-sm pb-3 mb-0" >
                    <div class="card-header" >
                        <h5 class="text-dark mb-0"><b>Add Patient</b></h5>
                    </div>
                    <div class="card-body mb-0" >
                        <form>
                            <div class="row">
                                <div class="col-md-6" >
                                    <label>Patient Name</label>
                                    <input type="text" class="form-control" placeholder="Enter Patient Name">
                                </div>
                                <div class="col-md-6" >
                                    <label>Patient Email</label>
                                    <input type="email" class="form-control" placeholder="Enter Patient Email">
                                </div>
                                <div class="col-md-12  mt-3" >
                                    <label>Patient Address</label>
                                    <input type="text" class="form-control" placeholder="Enter Patient Address">
                                </div>
                                <div class="col-md-4 mt-3" >
                                    <label>Patient Age</label>
                                    <input type="text" class="form-control" placeholder="Enter Patient Age">
                                </div>
                                <div class="col-md-4 mt-3" >
                                    <label>Patient Blood Type</label>
                                    <select class="form-control">
                                        <option selected value="A+">A Postive</option>
                                        <option value="A-">A Negative</option>
                                        <option value="B+">B Postive</option>
                                        <option value="B-">B Negative</option>
                                        <option value="AB+">AB Postive</option>
                                        <option value="AB-">AB Negative</option>
                                        <option value="O+">O Postive</option>
                                        <option value="O-">O Negative</option>
                                    </select>
                                </div>
                                <div class="col-md-4  mt-3" >
                                    <label>Patient Telephone</label>
                                    <input type="text" class="form-control" placeholder="Enter Patient Telephone">
                                </div>
                                <div class="col-md-6 mt-3" >
                                    <label>Patient Password</label>
                                    <input type="text" class="form-control" placeholder="Enter Password">
                                </div>
                                <div class="col-md-6 mt-3" >
                                    <label>Confirm Password</label>
                                    <input type="email" class="form-control" placeholder="Enter Confirm Password">
                                </div>
                                <div class="col-md-12 mt-3" >
                                    <div class="d-flex mb-0 mt-2" >
                                        <button class="ml-auto btn btn-md btn-success font-weight-bold px-2" type="submit">Submit</button>
                                        <button class="btn btn-md btn-danger font-weight-bold px-2 ml-2" type="reset">Clear</button>
                                    </div>
                                </div>
                            </div>
                        </form>                   
                    </div>
                </div>
            </div>
           
        
            <div class="col-12 mt-3" >
                <table id="patients" class="table bg-white border">
                    <thead>
                      <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Address</th>
                        <th scope="col">Age</th>
                        <th scope="col">Blood Group</th>
                        <th scope="col">Telephone</th>
                        <th scope="col">Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                      </tr>                    
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="./main.js" ></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>   
</body>
</html>