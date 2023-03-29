import zio.{ ZIO, ZLayer }

trait InventoryService {

  def getById(id: String): Option[Inventory]

}

object InventoryService {
  val inventory = List(
    Inventory(
      id = ID("apollo-oss"),
      deprecatedProducts = List(
        DeprecatedProduct(
          sku = "apollo-federation-v1",
          `package` = "@apollo/federation-v1",
          reason = Some("Migrate to Federation V2"),
          createdBy = ZIO.serviceWithZIO[UserService](_.getUser)
        )
      )
    )
  )

  val inMemory = ZLayer.succeed(new InventoryService {
    def getById(id: String): Option[Inventory] = inventory.find(_.id.id == id)
  })
}
