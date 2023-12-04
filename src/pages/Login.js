import Base from "../components/Base";
import { Button, Card, CardBody, CardHeader, Container, Form, FormGroup, Label, Input } from "reactstrap";
import '../styles/login.css'; // Import your custom CSS file
const Login=()=>{
    return (
        <Base>
        <Container>
        <Card>
          <CardHeader >
            Login
          </CardHeader>
          <CardBody>
            <Form>
           

              <FormGroup>
                <Label for="email">Email</Label>
                <Input
                  type="email"
                  id="email"
                  placeholder="Enter your email here"
                  className="form-control"
                />
              </FormGroup>

              <FormGroup>
                <Label for="password">Password</Label>
                <Input
                  type="password"
                  id="password"
                  placeholder="Enter your password here"
                  className="form-control"
                />
              </FormGroup>

              <div className="text-center">
                <Button className="mr-2">
                  Sign in
                </Button>
                <Button color="secondary" type="reset">
                  Reset
                </Button>
              </div>
            </Form>
          </CardBody>
        </Card>
      </Container>
        
        </Base>
        
    )
}

export default Login;