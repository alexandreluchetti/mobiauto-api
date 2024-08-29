package br.com.mobiauto.mobiauto_server.entrypoint.user.controller;

import br.com.mobiauto.mobiauto_server.core.useCase.user.UserUseCase;
import br.com.mobiauto.mobiauto_server.entrypoint.shared.DefaultResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.user.dto.CreateUserDto2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Admin")
@RequestMapping("/admin")
public class AdminController {

    private final UserUseCase useCase;

    public AdminController(UserUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/user")
    @Operation
//    @PreAuthorize("hasRole('ADMINISTRADOR')")
//    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<DefaultResponseDto> createUser(
            @Valid
            @RequestBody
            CreateUserDto2 createUserDto
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication: " + authentication);
        System.out.println("Authorities: " + authentication.getAuthorities());

        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMINISTRADOR"));

        if (!hasAdminRole) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        DefaultResponseDto defaultResponseDto = useCase.createUser(createUserDto.toObject()).toDto();
        return ResponseEntity.ok(defaultResponseDto);
    }
}
