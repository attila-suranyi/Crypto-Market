package com.codecool.cryptomarketjsonclasses.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class StockAppUser {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private double balance;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    //@Builder.Default
    private List<Long> walletIdList;

    @ElementCollection(fetch = FetchType.LAZY)
    //@Builder.Default
    private List<Long> transactionIdList;
}
