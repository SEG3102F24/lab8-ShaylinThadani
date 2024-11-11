package seg3x02.employeeGql.resolvers

// Import necessary libraries
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import java.util.*

@Controller
class EmployeesResolver(private val employeesRepository: EmployeesRepository) {

    // Query to fetch all employes

    @QueryMapping
    fun employees(): List<Employee> = employeesRepository.findAll()

    // Mutation to add a new emploee to the databaase

    @MutationMapping
    fun newEmployee(@Argument createEmployeeInput: CreateEmployeeInput): Employee {

        // Create a new Employee instnace using the input fields
        val employee = Employee(
            name = createEmployeeInput.name ?: "",
            dateOfBirth = createEmployeeInput.dateOfBirth ?: "",


            city = createEmployeeInput.city ?: "",
            salary = createEmployeeInput.salary ?: 0.0f,


            gender = createEmployeeInput.gender,
            email = createEmployeeInput.email
        )
        
        // Generate ID for the new employee
        employee.id = UUID.randomUUID().toString()
        
        // Save the new employee to the database
        employeesRepository.save(employee)
        
        // Return the newly created employee object
        return employee
        
    }


}