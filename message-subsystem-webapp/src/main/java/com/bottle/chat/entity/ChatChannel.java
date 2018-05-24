package com.bottle.chat.entity;

import com.bottle.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name="chatChannel")
public class ChatChannel {

  public ChatChannel(User firstUser, User secondUser) {
    this.id = UUID.randomUUID();
    this.firstUser = firstUser;
    this.secondUser = secondUser;
  }

  @Id
  @NotNull
  private UUID id;

  @OneToOne
  @JoinColumn(name = "firstUser")
  private User firstUser;

  @OneToOne
  @JoinColumn(name = "secondUser")
  private User secondUser;

}