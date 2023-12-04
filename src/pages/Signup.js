import React, { useState } from "react";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Container,
  Form,
  FormGroup,
  Label,
  Input,
} from "reactstrap";
import Base from "../components/Base";

const Signup = () => {
  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
    about: "",
    address: "", // Add a new field for address
    phone: "", // Add a new field for phone number
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  const handleChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
  };

  const resetData = () => {
    setData({
      name: "",
      email: "",
      password: "",
      about: "",
      address: "",
      phone: "",
    });
  };

  const submitForm=(event)=>{
    event.preventDefault();
    //validate data
    //correct data=call api
    console.log(data);
  }
  return (
    <Base>
      <Container>
        <Card>
          <CardHeader className="bg-primary text-white">
            Fill in your information to register
          </CardHeader>
          <CardBody>
            <Form onSubmit={submitForm}>
              <FormGroup>
                <Label for="name">Name</Label>
                <Input
                  type="text"
                  id="name"
                  placeholder="Enter your name here"
                  className="form-control"
                  onChange={(e) => handleChange(e, "name")}
                  value={data.name}
                />
              </FormGroup>

              <FormGroup>
                <Label for="email">Email</Label>
                <Input
                  type="email"
                  id="email"
                  placeholder="Enter your email here"
                  className="form-control"
                  onChange={(e) => handleChange(e, "email")}
                  value={data.email}
                />
              </FormGroup>

              <FormGroup>
                <Label for="password">Password</Label>
                <Input
                  type="password"
                  id="password"
                  placeholder="Enter your password here"
                  className="form-control"
                  onChange={(e) => handleChange(e, "password")}
                  value={data.password}
                />
              </FormGroup>

              <FormGroup>
                <Label for="about">About</Label>
                <textarea
                  id="about"
                  placeholder="Tell us about yourself"
                  className="form-control"
                  onChange={(e) => handleChange(e, "about")}
                  value={data.about}
                />
              </FormGroup>

              <FormGroup>
                <Label for="address">Address</Label>
                <Input
                  type="text"
                  id="address"
                  placeholder="Enter your address here"
                  className="form-control"
                  onChange={(e) => handleChange(e, "address")}
                  value={data.address}
                />
              </FormGroup>

              <FormGroup>
                <Label for="phone">Phone</Label>
                <Input
                  type="tel"
                  id="phone"
                  placeholder="Enter your phone number here"
                  className="form-control"
                  onChange={(e) => handleChange(e, "phone")}
                  value={data.phone}
                />
              </FormGroup>

              <div className="text-center">
                <Button color="primary" className="mr-2">
                  Register
                </Button>
                <Button onClick={resetData} color="secondary" type="reset">
                  Reset
                </Button>
              </div>
            </Form>
          </CardBody>
        </Card>
      </Container>
    </Base>
  );
};

export default Signup;
