import CustomNavBar from "./CustomNavbar";

const Base = ({ title = "Welcome to our website", children }) => {
    return (
      <div className="container-fluid m-0 p-0">
        <CustomNavBar/>
        {children}
      </div>
    );
  };
  
  export default Base;
  