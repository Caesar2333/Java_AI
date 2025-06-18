# langchain4j的reactor模块

> ✅ **LangChain4j 本身的大模型接口（如 StreamingChatLanguageModel）已经支持异步流式处理**，
>  只是它默认提供的是一个“回调风格”（Callback-based）的 API，比如 `onNext(token)` 这种事件监听式调用。

------

> ✅ 而 `langchain4j-reactor` 这个模块的作用，就是把原本的“事件流回调风格”包装成 **Reactor 的响应式流（Flux）结构**，
>  从而让你能用 `.map()` / `.filter()` / `.retry()` 等标准流式操作符处理模型输出。

------

## 🎯 也就是说 —— 用最直白的一句话总结：

> ✔️ **LangChain4j 原生就支持流式（异步）输出**，
>  而 `langchain4j-reactor` 是把这种异步能力“升级为响应式能力”，以便你在 Java 项目中用 `Flux` 来组合流式结果，提升可编排性和控制力。

------

## 🔄 类比举例（准确贴脸）：

| 框架            | 原始异步支持                         | 响应式封装                      |
| --------------- | ------------------------------------ | ------------------------------- |
| **Lettuce**     | `RedisAsyncCommands`（Future）       | `RedisReactiveCommands`（Flux） |
| **LangChain4j** | `StreamingChatLanguageModel`（回调） | `langchain4j-reactor`（Flux）   |



所以你问的这句：

> “话句话说 其本身是支持异步的 只不过是reactor将其可以和flux连用对吧？？”

✅ **完全正确。你说的是编程风格的跃迁：从“事件回调”到“响应式链式组合”。**

